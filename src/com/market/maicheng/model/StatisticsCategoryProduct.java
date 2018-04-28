package com.market.maicheng.model;
/**
 * 分类与商户关系表
 * @author 高红飞
 *
 */
public class StatisticsCategoryProduct {
	/**
	 * 商品类别
	 */
	private long cId;
	/**
	 * 商户ID
	 */
	private long mId;
	
	/**
	 * 统计的商品数
	 */
	private long products;
	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	public long getmId() {
		return mId;
	}

	public void setmId(long mId) {
		this.mId = mId;
	}

	public long getProducts() {
		return products;
	}

	public void setProducts(long products) {
		this.products = products;
	}


}
