package com.market.maicheng.model;
/**
 * 同品关系表
 * @author 高红飞
 *
 */
public class SameGood {
	/**
	 * 同品关系编码
	 */
	private long id;
	/**
	 * 商户id
	 */
	private long mid;

	/**
	 * 同品业务编码
	 */
	private String sid;
	/**
	 * 二维码
	 */
	private String barcodeId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getBarcodeId() {
		return barcodeId;
	}
	public void setBarcodeId(String barcodeId) {
		this.barcodeId = barcodeId;
	}
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
}
