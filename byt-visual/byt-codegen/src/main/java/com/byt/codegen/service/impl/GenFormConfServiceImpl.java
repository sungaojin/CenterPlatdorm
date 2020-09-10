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
package com.byt.codegen.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byt.codegen.entity.ColumnEntity;
import com.byt.codegen.entity.GenFormConf;
import com.byt.codegen.mapper.GenFormConfMapper;
import com.byt.codegen.mapper.GeneratorMapper;
import com.byt.codegen.service.GenFormConfService;
import com.byt.codegen.util.GenUtils;
import com.byt.common.datasource.support.DynamicDataSourceContextHolder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 表单管理
 *
 * @author liyang
 * @date 2019-08-12 15:55:35
 */
@Service
@AllArgsConstructor
public class GenFormConfServiceImpl extends ServiceImpl<GenFormConfMapper, GenFormConf> implements GenFormConfService {
	private final GeneratorMapper generatorMapper;

	/**
	 * 1. 根据数据源、表名称，查询已配置表单信息
	 * 2. 不存在调用模板生成
	 *
	 * @param dsId      数据源ID
	 * @param tableName 表名称
	 * @return
	 */
	@Override
	@SneakyThrows
	public String getForm(Integer dsId, String tableName) {
		GenFormConf form = getOne(Wrappers.<GenFormConf>lambdaQuery()
				.eq(GenFormConf::getTableName, tableName)
				.orderByDesc(GenFormConf::getCreateTime), false);

		if (form != null) {
			return form.getFormInfo();
		}

		DynamicDataSourceContextHolder.setDataSourceType(dsId);
		List<Map<String, String>> columns = generatorMapper.queryColumns(tableName);
		//设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", ClasspathResourceLoader.class.getName());
		Velocity.init(prop);
		Template template = Velocity.getTemplate("template/crud.js.vm", CharsetUtil.UTF_8);
		VelocityContext context = new VelocityContext();

		List<ColumnEntity> columnList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setLowerAttrName(StringUtils.uncapitalize(GenUtils.columnToJava(column.get("columnName"))));
			columnList.add(columnEntity);
		}
		context.put("columns", columnList);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return StrUtil.trim(StrUtil.removePrefix(writer.toString(), GenUtils.CRUD_PREFIX));
	}

}
