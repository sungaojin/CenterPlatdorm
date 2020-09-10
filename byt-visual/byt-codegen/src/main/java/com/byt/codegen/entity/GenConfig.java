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

package com.byt.codegen.entity;

import lombok.Data;

/**
 * @author liyang
 * @date 2020/02/2
 * 生成配置
 */
@Data
public class GenConfig {
	/**
	 * 数据源ID
	 */
	private Integer id;
	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 表前缀
	 */
	private String tablePrefix;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 表备注
	 */
	private String comments;
}
