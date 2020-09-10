/*
 *    Copyright (c) 2018-2025, liyang All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the byt.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liyang (wangiegie@gmail.com)
 */

package com.byt.pay.handler.impl;

import cn.hutool.extra.servlet.ServletUtil;
import com.byt.pay.config.PayCommonProperties;
import com.byt.pay.mapper.PayGoodsOrderMapper;
import com.byt.pay.mapper.PayTradeOrderMapper;
import com.byt.pay.utils.OrderStatusEnum;
import com.byt.pay.utils.PayChannelNameEnum;
import com.jpay.ext.kit.PaymentKit;
import com.jpay.weixin.api.WxPayApi;
import com.jpay.weixin.api.WxPayApiConfigKit;
import com.byt.common.data.tenant.TenantContextHolder;
import com.byt.pay.entity.PayGoodsOrder;
import com.byt.pay.entity.PayTradeOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liyang
 * @date 2020-02-31
 * <p>
 * 微信公众号支付
 */
@Slf4j
@Service("WEIXIN_MP")
public class WeChatMpPayOrderHandler extends AbstractPayOrderHandler {
	@Autowired
	private PayCommonProperties payCommonProperties;
	@Autowired
	private PayTradeOrderMapper tradeOrderMapper;
	@Autowired
	private PayGoodsOrderMapper goodsOrderMapper;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 创建交易订单
	 *
	 * @param goodsOrder
	 * @return
	 */
	@Override
	public PayTradeOrder createTradeOrder(PayGoodsOrder goodsOrder) {
		PayTradeOrder tradeOrder = new PayTradeOrder();
		tradeOrder.setOrderId(goodsOrder.getPayOrderId());
		tradeOrder.setAmount(goodsOrder.getAmount());
		tradeOrder.setChannelId(PayChannelNameEnum.WEIXIN_MP.getName());
		tradeOrder.setChannelMchId(WxPayApiConfigKit.getWxPayApiConfig().getMchId());
		tradeOrder.setClientIp(ServletUtil.getClientIP(request));
		tradeOrder.setCurrency("CNY");
		tradeOrder.setStatus(OrderStatusEnum.INIT.getStatus());
		tradeOrder.setBody(goodsOrder.getGoodsName());
		tradeOrderMapper.insert(tradeOrder);
		return tradeOrder;
	}

	/**
	 * 调起渠道支付
	 *
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 */
	@Override
	public Object pay(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder) {
		String ip = ServletUtil.getClientIP(request);
		Map<String, String> params = WxPayApiConfigKit.getWxPayApiConfig()
				.setAttach(TenantContextHolder.getTenantId().toString())
				.setBody(goodsOrder.getGoodsName())
				.setSpbillCreateIp(ip)
				.setTotalFee(goodsOrder.getAmount())
				.setOpenId(goodsOrder.getUserId())
				.setTradeType(WxPayApi.TradeType.JSAPI)
				.setNotifyUrl(payCommonProperties.getWxPayConfig().getNotifyUrl())
				.setOutTradeNo(tradeOrder.getOrderId())
				.build();

		String xmlResult = WxPayApi.pushOrder(false, params);
		log.info(xmlResult);
		Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);
		String prepayId = resultMap.get("prepay_id");
		return PaymentKit.prepayIdCreateSign(prepayId);
	}

	/**
	 * 更新订单信息
	 *
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 */
	@Override
	public void updateOrder(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder) {
		tradeOrderMapper.updateById(tradeOrder);
		goodsOrderMapper.updateById(goodsOrder);
	}
}
