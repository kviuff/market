package com.market.maicheng.model;

public class ShopCar {
	/**
	 * 主键id
	 */	
	private long id;
	/**
	 * 店铺id
	 */	
	private long mid;

	/**
	 * 会员ID
	 */
	private long memberid;
	/**
	 * 商品ID
	 */
	private long pid;
	/**
	 * 购买数量
	 */
	private int count;
	/**
	 * 价格表id
	 */
	private long barcodepriceid;
	/**
	 * 添加时间
	 */
	private long addtime;
	/**
	 * 是否拼单 0 不 1是
	 */
	private int ping;
	
	/**
	 * 店铺名称
	 */
	private String mname;
	
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMemberid() {
		return memberid;
	}
	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public long getBarcodepriceid() {
		return barcodepriceid;
	}
	public void setBarcodepriceid(long barcodepriceid) {
		this.barcodepriceid = barcodepriceid;
	}
	public long getAddtime() {
		return addtime;
	}
	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
	public int getPing() {
		return ping;
	}
	public void setPing(int ping) {
		this.ping = ping;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	
}
