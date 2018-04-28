package com.market.maicheng.model;

/**
 * 品牌
 * @author Shinobi
 *
 */
public class Brand {
	private long id;
	private long pid;
	private String brandName;
	/**
	 * 品牌首字母
	 */
	private String brandInitial;


	/**
	 * 是否删除 0未删除 1删除
	 */
	private int isDel;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandInitial() {
		return brandInitial;
	}

	public void setBrandInitial(String brandInitial) {
		this.brandInitial = brandInitial;
	}
	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
