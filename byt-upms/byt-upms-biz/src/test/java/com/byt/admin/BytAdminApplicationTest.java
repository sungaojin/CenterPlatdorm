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

package com.byt.admin;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author liyang
 * @date 2020/02/7
 * <p>
 */
public class BytAdminApplicationTest {
	@Test
	public void test1() {
		// 对应application-dev.yml 中配置的根密码
		System.setProperty("jasypt.encryptor.password", "byt");
		StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());

		//加密方法
		System.out.println(stringEncryptor.encrypt("root"));
		System.out.println(stringEncryptor.encrypt("gen"));
		System.out.println(stringEncryptor.encrypt("byt"));
		System.out.println(stringEncryptor.encrypt("app"));

	}
	@Test
	public void test2() {
		System.setProperty("jasypt.encryptor.password", "byt");
		StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
		//解密方法
		System.out.println(stringEncryptor.decrypt("GOZoZTbKkYaorfI70ZWBJw=="));
		System.out.println(stringEncryptor.decrypt("HIo3cjGqq+tce1dVQUF1sA=="));
		System.out.println(stringEncryptor.decrypt("mJFmFwRq4+brYI0S6U4VgA=="));
		System.out.println(stringEncryptor.decrypt("hKbl2x5f5PjUQaIJzFB3Ng=="));
	}
}
