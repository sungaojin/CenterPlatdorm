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

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byt.codegen.entity.GenDatasourceConf;
import com.byt.codegen.mapper.GenDatasourceConfMapper;
import com.byt.codegen.service.GenDatasourceConfService;
import com.byt.common.datasource.config.DynamicDataSourceConfig;
import lombok.AllArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;

/**
 * 数据源表
 *
 * @author liyang
 * @date 2019-03-31 16:00:20
 */
@Service
@AllArgsConstructor
public class GenDatasourceConfServiceImpl extends ServiceImpl<GenDatasourceConfMapper, GenDatasourceConf> implements GenDatasourceConfService {
	private final DynamicDataSourceConfig dynamicDataSourceConfig;
	private final StringEncryptor stringEncryptor;

	/**
	 * 保存数据源并且加密
	 *
	 * @param genDatasourceConf
	 * @return
	 */
	@Override
	public Boolean saveDsByEnc(GenDatasourceConf genDatasourceConf) {
		genDatasourceConf.setPassword(stringEncryptor.encrypt(genDatasourceConf.getPassword()));
		this.baseMapper.insert(genDatasourceConf);
		return dynamicDataSourceConfig.reload();
	}

	/**
	 * 更新数据源
	 *
	 * @param genDatasourceConf
	 * @return
	 */
	@Override
	public Boolean updateDsByEnc(GenDatasourceConf genDatasourceConf) {
		if (StrUtil.isNotBlank(genDatasourceConf.getPassword())) {
			genDatasourceConf.setPassword(stringEncryptor.encrypt(genDatasourceConf.getPassword()));
		}
		this.baseMapper.updateById(genDatasourceConf);
		return dynamicDataSourceConfig.reload();
	}
}
