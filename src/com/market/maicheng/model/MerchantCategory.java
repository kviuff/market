package com.market.maicheng.model;

public class MerchantCategory {
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
//	public long getCid() {
//		return cid;
//	}
//	public void setCid(long cid) {
//		this.cid = cid;
//	}
//	public long getMid() {
//		return mid;
//	}
//	public void setMid(long mid) {
//		this.mid = mid;
//	}
	public String getCateName() {
		return cateName;
	}
	public void setcName(String cateName) {
		this.cateName = cateName;
	}
	private long id;
//	/**
//	 * 分类编号
//	 */
//	private long cid;
	/**
	 * 分类中文名
	 */
	private String cateName;

//	/**
//	 * 商家编号
//	 */
//	private long mid;
	
	

}
