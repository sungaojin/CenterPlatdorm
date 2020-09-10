package com.byt.pay.config;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jpay.alipay.AliPayApiConfig;
import com.jpay.alipay.AliPayApiConfigKit;
import com.jpay.weixin.api.WxPayApiConfig;
import com.jpay.weixin.api.WxPayApiConfigKit;
import com.byt.admin.api.feign.RemoteTenantService;
import com.byt.common.core.constant.CommonConstants;
import com.byt.common.core.constant.SecurityConstants;
import com.byt.common.data.tenant.TenantContextHolder;
import com.byt.pay.entity.PayChannel;
import com.byt.pay.service.PayChannelService;
import com.byt.pay.utils.PayChannelNameEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyang
 * @date 2020-02-31
 * <p>
 * 支付参数初始化
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class PayConfigParmaInitRunner {
	public static Map<String, WxMpService> mpServiceMap = new HashMap<>();
	private final PayChannelService channelService;
	private final RemoteTenantService tenantService;


	@Async
	@Order
	@SqlParser(filter = true)
	@EventListener({WebServerInitializedEvent.class})
	public void initPayConfig() {

		List<PayChannel> channelList = new ArrayList<>();
		tenantService.list(SecurityConstants.FROM_IN).getData()
				.forEach(tenant -> {
					TenantContextHolder.setTenantId(tenant.getId());
					List<PayChannel> payChannelList = channelService
							.list(Wrappers.<PayChannel>lambdaQuery()
									.eq(PayChannel::getState, CommonConstants.STATUS_NORMAL));
					channelList.addAll(payChannelList);
				});

		channelList.forEach(channel -> {
			JSONObject params = JSONUtil.parseObj(channel.getParam());

			//支付宝支付
			if (PayChannelNameEnum.ALIPAY_WAP.getName().equals(channel.getChannelId())) {

				AliPayApiConfig aliPayApiConfig = AliPayApiConfig.New()
						.setAppId(channel.getAppId())
						.setPrivateKey(params.getStr("privateKey"))
						.setCharset(CharsetUtil.UTF_8)
						.setAlipayPublicKey(params.getStr("publicKey"))
						.setServiceUrl(params.getStr("serviceUrl"))
						.setSignType("RSA2")
						.build();

				AliPayApiConfigKit.putApiConfig(aliPayApiConfig);
				log.info("新增支付宝支付参数 {}", aliPayApiConfig);
			}

			// 微信支付
			if (PayChannelNameEnum.WEIXIN_MP.getName().equals(channel.getChannelId())) {
				WxPayApiConfig wx = WxPayApiConfig.New()
						.setAppId(channel.getAppId())
						.setMchId(channel.getChannelMchId())
						.setPaternerKey(params.getStr("paternerKey"))
						.setPayModel(WxPayApiConfig.PayModel.BUSINESSMODEL);

				String subMchId = params.getStr("subMchId");
				if (StrUtil.isNotBlank(subMchId)) {
					wx.setPayModel(WxPayApiConfig.PayModel.SERVICEMODE);
					wx.setSubMchId(subMchId);
				}

				WxPayApiConfigKit.putApiConfig(wx);

				WxMpService wxMpService = new WxMpServiceImpl();
				WxMpDefaultConfigImpl storage = new WxMpDefaultConfigImpl();
				storage.setAppId(channel.getAppId());
				storage.setSecret(params.getStr("secret"));
				storage.setToken(params.getStr("token"));
				wxMpService.setWxMpConfigStorage(storage);

				mpServiceMap.put(channel.getAppId(), wxMpService);
				log.info("新增微信支付参数 {} {}", wx, wxMpService);
			}
		});
	}
}
