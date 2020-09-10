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

package com.byt.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 图形验证码
 *
 * @author liyang
 * @date 2020-02-18
 */
@Data
@ApiModel(value =  "图形验证码")
public class ImageCode implements Serializable {
	/**
	 * 随机数
	 */
	@ApiModelProperty(value = "随机数")
	private String code;
	/**
	 * 验证码过期时间
	 */
	@ApiModelProperty(value = "验证码过期时间")
	private LocalDateTime expireTime;
	/**
	 * 验证码图片
	 */
	@ApiModelProperty(value = "验证码图片")
	private BufferedImage image;

	public ImageCode(BufferedImage image, String sRand, int defaultImageExpire) {
		this.image = image;
		this.code = sRand;
		this.expireTime = LocalDateTime.now().plusSeconds(defaultImageExpire);
	}
}
