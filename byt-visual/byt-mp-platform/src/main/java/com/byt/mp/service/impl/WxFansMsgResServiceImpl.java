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
package com.byt.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byt.common.core.util.R;
import com.byt.common.security.service.BytUser;
import com.byt.common.security.util.SecurityUtils;
import com.byt.mp.config.WxMpConfiguration;
import com.byt.mp.entity.WxFansMsg;
import com.byt.mp.entity.WxFansMsgRes;
import com.byt.mp.mapper.WxFansMsgMapper;
import com.byt.mp.mapper.WxFansMsgResMapper;
import com.byt.mp.service.WxFansMsgResService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 微信消息回复业务
 *
 * @author liyang
 * @date 2019-03-27 20:45:48
 */
@Slf4j
@Service
@AllArgsConstructor
public class WxFansMsgResServiceImpl extends ServiceImpl<WxFansMsgResMapper, WxFansMsgRes> implements WxFansMsgResService {
	private final WxFansMsgMapper wxFansMsgMapper;


	/**
	 * 保存并发送回复
	 * <p>
	 * 1. 根据消息ID 查询消息的所属 公众号信息
	 * 2. 构建文本客服消息并发送
	 * 3. 完善回复消息并保存
	 * 4. 更新微信消息为已回复
	 *
	 * @param wxFansMsgRes
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R saveAndSend(WxFansMsgRes wxFansMsgRes) {
		WxFansMsg wxFansMsg = wxFansMsgMapper.selectById(wxFansMsgRes.getFansMsgId());

		String wxAccountAppid = wxFansMsg.getWxAccountAppid();
		WxMpService wxMpService = WxMpConfiguration.getMpServices().get(wxAccountAppid);

		try {
			wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage
					.TEXT()
					.toUser(wxFansMsg.getOpenid())
					.content(wxFansMsgRes.getResContent())
					.build());
		} catch (WxErrorException e) {
			log.error("发送客服消息失败 ", e);
			return R.failed(e.getError().getErrorMsg());
		}

		BytUser user = SecurityUtils.getUser();
		wxFansMsgRes.setUserId(user.getId());
		wxFansMsgRes.setUserName(user.getUsername());
		baseMapper.insert(wxFansMsgRes);

		wxFansMsg.setIsRes("1");
		wxFansMsgMapper.updateById(wxFansMsg);
		return R.ok();
	}
}
