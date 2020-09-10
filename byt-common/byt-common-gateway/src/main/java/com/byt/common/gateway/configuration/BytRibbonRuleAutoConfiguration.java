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

package com.byt.common.gateway.configuration;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.byt.common.gateway.filter.GrayLoadBalancerClientFilter;
import com.byt.common.gateway.rule.AbstractDiscoveryEnabledRule;
import com.byt.common.gateway.rule.MetadataAwareRule;
import com.byt.common.gateway.support.BytRibbonRuleProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Mica ribbon rule auto configuration.
 *
 * @author liyang
 * @link https://github.com/lets-mica/mica
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnClass(NacosServer.class)
@AutoConfigureBefore(RibbonClientConfiguration.class)
@EnableConfigurationProperties(BytRibbonRuleProperties.class)
@ConditionalOnProperty(value = "ribbon.rule.enabled", matchIfMissing = true)
public class BytRibbonRuleAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public AbstractDiscoveryEnabledRule metadataAwareRule() {
		return new MetadataAwareRule();
	}

	@Bean
	@ConditionalOnMissingBean(LoadBalancerClient.class)
	public LoadBalancerClient loadBalancerClient(SpringClientFactory springClientFactory) {
		return new RibbonLoadBalancerClient(springClientFactory);
	}

	@Bean
	@ConditionalOnMissingBean(LoadBalancerClientFilter.class)
	public LoadBalancerClientFilter loadBalancerClientFilter(RibbonLoadBalancerClient client
			, LoadBalancerProperties properties) {
		return new GrayLoadBalancerClientFilter(client, properties);
	}

}
