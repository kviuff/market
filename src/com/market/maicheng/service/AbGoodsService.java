package com.market.maicheng.service;

import java.util.List;

import com.market.maicheng.model.AbGoods;

public interface AbGoodsService {

	/**
	 * 获取产品
	 * @return
	 */
	public List<AbGoods> getAbGoodss(long offset, long rows);
	
	public int getAbGoodssCount();
	
}
