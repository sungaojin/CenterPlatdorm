/*
 *
 *      Copyright (c) 2018-2025, liyang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the byt.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: liyang (wangiegie@gmail.com)
 *
 */

package com.byt.admin;


import com.byt.common.security.annotation.EnableBytFeignClients;
import com.byt.common.security.annotation.EnableBytResourceServer;
import com.byt.common.swagger.annotation.EnableBytSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author liyang
 * @date 2020年02月21日
 * <p>
 * 用户统一管理系统
 */
@EnableBytSwagger2
@SpringCloudApplication
@EnableBytFeignClients
@EnableBytResourceServer
public class BytAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(BytAdminApplication.class, args);
	}

}
