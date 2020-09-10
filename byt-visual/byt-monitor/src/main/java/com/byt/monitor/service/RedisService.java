package com.byt.monitor.service;

import java.util.Map;

/**
 * @author liyang
 * @date 2020-02-08
 * <p>
 * redis 监控
 */
public interface RedisService {
	/**
	 * 获取内存信息
	 *
	 * @return
	 */
	Map<String, Object> getInfo();
}
