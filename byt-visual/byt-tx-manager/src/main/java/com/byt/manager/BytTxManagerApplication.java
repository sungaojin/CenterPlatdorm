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

package com.byt.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author LCN
 * @author liyang
 * tx-manager ，进行了代码逻辑和代码规范重构
 */
@SpringCloudApplication
public class BytTxManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(BytTxManagerApplication.class, args);
	}

}
