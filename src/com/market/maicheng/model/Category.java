package com.market.maicheng.model;

import java.util.List;

public class Category {
	private long id;
	private String sid;
	
	/**
	 * 类别名称
	 */
	private String cateName;
	/**
	 * 是否删除 0 否 1是
	 */
	private int isdel;
	/**
	 * 父id
	 */
	private long pid;
	/**
	 * 父级名称
	 */
	private String pname;
	/**
	 * 是否首推 0 否 1是
	 */
	private int istop;
	/**
	 * 类别图片1
	 */
	private String icon;
	/**
	 * 类别图片2
	 */
	private String icon1;
	/**
	 * 类别图片3
	 */
	private String icon2;
	/**
	 * 排序
	 */
	private int sort;
	/**
	 * 是否是属性
	 */
	private String states;

	/**
	 * 层级
	 */
	private int level;
	
	/**
	 * 更新时间
	 */
	private long updateTime;


	/**
	 * 子类集合
	 */
	public List<Category> cateList;
	
	
	public String getIcon1() {
		return icon1;
	}

	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}

	public String getIcon2() {
		return icon2;
	}

	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getIstop() {
		return istop;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Category> getCateList() {
		return cateList;
	}

	public void setCateList(List<Category> cateList) {
		this.cateList = cateList;
	}

	public String getSid() {
		return this.getId()+"";
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
}
