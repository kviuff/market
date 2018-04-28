package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.market.maicheng.model.AbGoods;

/**
 * 产品表dao
 * @author Administrator
 *
 */
public interface AbGoodsDao {
	

	
	/**
	 * 获取产品
	 * @return
	 */
	public List<AbGoods> getAbGoodss(@Param("offset") long offset, @Param("rows") long rows);

	public int getAbGoodssCount();
}

