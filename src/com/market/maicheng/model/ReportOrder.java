package com.market.maicheng.model;

public class ReportOrder {
	/**
	 * 日期
	 */
	private String ordate;
	/**
	 * 数量
	 */
	private String ornum;
	
	/**
	 * 销量
	 */
	private Double totalmoney;
	
	public String getOrdate() {
		return ordate;
	}
	public void setOrdate(String ordate) {
		this.ordate = ordate;
	}
	public String getOrnum() {
		return ornum;
	}
	public void setOrnum(String ornum) {
		this.ornum = ornum;
	}
	public Double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}
	
}
