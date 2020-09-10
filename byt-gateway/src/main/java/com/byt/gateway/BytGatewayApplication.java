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

package com.byt.gateway;


import com.byt.common.gateway.annotation.EnableBytDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author liyang
 * @date 2020年02月21日
 * 网关应用
 */
@EnableBytDynamicRoute
@SpringCloudApplication
public class BytGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytGatewayApplication.class, args);
	}
}
