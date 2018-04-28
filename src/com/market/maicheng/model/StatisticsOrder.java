package com.market.maicheng.model;

public class StatisticsOrder {
	/**
	 * 商户ID
	 */
	private long merchantId;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 订单数量
	 */
	private Integer orderCount;
	
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
}
