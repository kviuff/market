package com.market.maicheng.model;
/**
 * 功能:收藏店铺实体
 * 创建时间: 2017/12/20.14:45
 *
 * @author 高红飞(gaohongfeipc)
 * @version(版本)：TODO
 * @since JDK 7
 */
public class Favorite {
	private long id;

	/**
	 * 用户id
	 */
	private long createid;
	/**
	 * 收藏的商家id
	 */
	private long mid;
	/**
	 * 是否被删除
	 */
	private int isdel;
	/**
	 * 添加时间
	 */
	private long createtime;
	
	public long getCreateid() {
		return createid;
	}
	public void setCreateid(long createid) {
		this.createid = createid;
	}
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
