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

package com.byt.manager.compensate.dao.impl;

import com.alibaba.fastjson.JSON;
import com.byt.manager.config.ConfigReader;
import com.lorne.core.framework.utils.DateUtil;
import com.byt.manager.compensate.dao.CompensateDao;
import com.byt.manager.compensate.model.TransactionCompensateMsg;
import com.byt.manager.redis.RedisServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyang on 2020/02/11
 */
@Service
public class CompensateDaoImpl implements CompensateDao {


	@Autowired
	private RedisServerService redisServerService;

	@Autowired
	private ConfigReader configReader;


	@Override
	public String saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg) {

		String name = String.format("%s%s:%s:%s.json", configReader.getKeyPrefixCompensate(), transactionCompensateMsg.getModel(), DateUtil.getCurrentDateFormat(), transactionCompensateMsg.getGroupId());

		String json = JSON.toJSONString(transactionCompensateMsg);

		redisServerService.saveCompensateMsg(name, json);

		return name;
	}


	@Override
	public List<String> loadCompensateKeys() {
		String key = configReader.getKeyPrefixCompensate() + "*";
		return redisServerService.getKeys(key);
	}


	@Override
	public boolean hasCompensate() {
		String key = configReader.getKeyPrefixCompensate() + "*";
		List<String> keys = redisServerService.getKeys(key);
		return keys != null && keys.size() > 0;
	}

	@Override
	public List<String> loadCompensateTimes(String model) {
		String key = configReader.getKeyPrefixCompensate() + model + ":*";
		List<String> keys = redisServerService.getKeys(key);
		List<String> times = new ArrayList<String>();
		for (String k : keys) {
			if (k.length() > 36) {
				String time = k.substring(k.length() - 24, k.length() - 14);
				if (!times.contains(time)) {
					times.add(time);
				}
			}
		}
		return times;
	}


	@Override
	public List<String> loadCompensateByModelAndTime(String path) {
		String key = String.format("%s%s*", configReader.getKeyPrefixCompensate(), path);
		List<String> keys = redisServerService.getKeys(key);
		List<String> values = redisServerService.getValuesByKeys(keys);
		return values;
	}

	@Override
	public String getCompensate(String path) {
		String key = String.format("%s%s.json", configReader.getKeyPrefixCompensate(), path);
		return redisServerService.getValueByKey(key);
	}


	@Override
	public void deleteCompensateByPath(String path) {
		String key = String.format("%s%s.json", configReader.getKeyPrefixCompensate(), path);
		redisServerService.deleteKey(key);
	}


	@Override
	public void deleteCompensateByKey(String key) {
		redisServerService.deleteKey(key);
	}

	@Override
	public String getCompensateByGroupId(String groupId) {
		String key = String.format("%s*%s.json", configReader.getKeyPrefixCompensate(), groupId);
		List<String> keys = redisServerService.getKeys(key);
		if (keys != null && keys.size() == 1) {
			String k = keys.get(0);
			return redisServerService.getValueByKey(k);
		}
		return null;
	}
}
