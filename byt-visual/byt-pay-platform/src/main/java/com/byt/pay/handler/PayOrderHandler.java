package com.byt.pay.handler;

import com.byt.pay.entity.PayGoodsOrder;
import com.byt.pay.entity.PayTradeOrder;

/**
 * @author liyang
 * @date 2020-02-31
 * <p>
 * 支付业务
 */
public interface PayOrderHandler {

	/**
	 * 创建商品订单
	 *
	 * @param goodsOrder 金额
	 * @return
	 */
	void createGoodsOrder(PayGoodsOrder goodsOrder);

	/**
	 * 创建交易订单
	 *
	 * @param goodsOrder 商品订单
	 * @return
	 */
	PayTradeOrder createTradeOrder(PayGoodsOrder goodsOrder);

	/**
	 * 调起渠道支付
	 *
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 * @return obj
	 */
	Object pay(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder);

	/**
	 * 更新订单信息
	 *
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 */
	void updateOrder(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder);

	/**
	 * 调用入口
	 *
	 * @param goodsOrde 商品订单
	 * @return
	 */
	Object handle(PayGoodsOrder goodsOrde);
}
