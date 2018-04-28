package com.market.maicheng.model;

public class News {
	private long id;
	/**
	 * \新闻标题
	 */
	private String title;
	/**
	 * 新闻内容
	 */
	private String content;
	/**
	 * 添加时间
	 */
	private long addtime;
	/**
	 * 添加人
	 */
	private long author;
	/**
	 * 是否删除 1删除
	 */
	private int isdel;
	/**
	 * 0 后台发布 1 前台发布
	 */
	private int types;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getAddtime() {
		return addtime;
	}
	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
	public long getAuthor() {
		return author;
	}
	public void setAuthor(long author) {
		this.author = author;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}

}
