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

package com.byt.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byt.pay.entity.PayGoodsOrder;

import java.util.Map;

/**
 * 商品
 *
 * @author liyang
 * @date 2020-02-28 23:58:27
 */
public interface PayGoodsOrderService extends IService<PayGoodsOrder> {

	/**
	 * 购买商品
	 *
	 * @param goodsOrder goods
	 * @return
	 */
	Map<String, Object> buy(PayGoodsOrder goodsOrder);

}