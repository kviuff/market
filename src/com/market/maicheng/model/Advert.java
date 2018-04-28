package com.market.maicheng.model;

/**
 * 广告
 * @author Snake
 *
 */
public class Advert {
	private long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 链接地址
	 */
	private String link;
	/**
	 * 图片路径
	 */
	private String url;
	/**
	 * 所属板块
	 */
	private long type;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
