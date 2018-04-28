package com.market.maicheng.model;

import java.util.List;

public class ShopCarVo {
	/**
	 * 店铺id
	 */	
	private long mid;

	/**
	 * 店铺名称
	 */
	private String mname;
	/**
	 * 购物车列表
	 */
	private List<ShopCarApi> carList;
	
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public List<ShopCarApi> getCarList() {
		return carList;
	}
	public void setCarList(List<ShopCarApi> carList) {
		this.carList = carList;
	}
	
	
}
