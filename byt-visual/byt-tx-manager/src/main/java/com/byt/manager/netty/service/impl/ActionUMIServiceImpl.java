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

package com.byt.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.byt.manager.framework.utils.SocketManager;
import com.byt.manager.manager.ModelInfoManager;
import com.byt.manager.model.ModelInfo;
import com.byt.manager.netty.service.IActionService;
import org.springframework.stereotype.Service;

/**
 * 上传模块信息
 * @author liyang on 2020/02/11
 */
@Service(value = "umi")
public class ActionUMIServiceImpl implements IActionService {


	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res = "1";

		String uniqueKey = params.getString("u");
		String ipAddress = params.getString("i");
		String model = params.getString("m");


		ModelInfo modelInfo = new ModelInfo();
		modelInfo.setChannelName(channelAddress);
		modelInfo.setIpAddress(ipAddress);
		modelInfo.setModel(model);
		modelInfo.setUniqueKey(uniqueKey);

		ModelInfoManager.getInstance().addModelInfo(modelInfo);

		SocketManager.getInstance().onLine(channelAddress, uniqueKey);

		return res;
	}
}
