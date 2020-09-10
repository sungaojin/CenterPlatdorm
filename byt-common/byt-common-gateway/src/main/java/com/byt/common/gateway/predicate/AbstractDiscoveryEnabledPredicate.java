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

package com.byt.common.gateway.predicate;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;

/**
 * 过滤服务
 *
 * @author liyang
 */
public abstract class AbstractDiscoveryEnabledPredicate extends AbstractServerPredicate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean apply(@Nullable PredicateKey input) {
		return input != null
				&& input.getServer() instanceof NacosServer
				&& apply((NacosServer) input.getServer(), (HttpHeaders) input.getLoadBalancerKey());
	}

	/**
	 * Returns whether the specific {@link NacosServer} matches this predicate.
	 *
	 * @param server  the discovered server
	 * @param headers 请求头
	 * @return whether the server matches the predicate
	 */
	abstract boolean apply(NacosServer server, HttpHeaders headers);
}
