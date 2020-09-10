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

package com.byt.act.service;

/**
 * @author liyang
 * @date 2020/02/25
 */
public interface EditorService {
	/**
	 * 获取设计器页面的json
	 *
	 * @return
	 */
	Object getStencilset();

	/**
	 * 根据modelId获取model
	 *
	 * @param modelId
	 * @return
	 */
	Object getEditorJson(String modelId);

	/**
	 * 保存model信息
	 * @param modelId
	 * @param name
	 * @param description
	 * @param jsonXml
	 * @param svgXml
	 */
	void saveModel(String modelId, String name, String description, String jsonXml, String svgXml);
}
