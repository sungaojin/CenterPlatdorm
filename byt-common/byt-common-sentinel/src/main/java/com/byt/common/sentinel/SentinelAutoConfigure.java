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

package com.byt.common.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.byt.common.sentinel.handle.BytUrlBlockHandler;
import com.byt.common.sentinel.parser.BytHeaderRequestOriginParser;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author liyang
 * <p>
 * sentinel 配置
 */
@Configuration
public class SentinelAutoConfigure {

	@PostConstruct
	public void initWebCallbackManager() {
		WebCallbackManager.setUrlBlockHandler(new BytUrlBlockHandler());
		WebCallbackManager.setRequestOriginParser(new BytHeaderRequestOriginParser());
	}

}
