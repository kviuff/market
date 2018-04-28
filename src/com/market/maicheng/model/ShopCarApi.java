package com.market.maicheng.model;

public class ShopCarApi {
	/**
	 * 主键id
	 */	
	private long id;
	/**
	 * 店铺id
	 */	
	private long mid;

	/**
	 * 图片
	 */
	private String img;
	/**
	 * 规格
	 */
	private String spec;
	/**
	 * 购买数量
	 */
	private int count;
	/**
	 * 品名
	 */
	private String brandname;
	/**
	 * 进价
	 */
	private String price;
	/**
	 * 会员价
	 */
	private String vipprice;
	/**
	 * 进货价
	 */
	private String stockprice;
	/**
	 * 是否拼单 0 不 1是
	 */
	private int ping;
	
	/**
	 * 店铺名称
	 */
	private String mname;
	
	/**
	 * 商品名称
	 */
	private String goodsname;
	
	/**
	 * 添加时间
	 */
	private String addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVipprice() {
		return vipprice;
	}

	public void setVipprice(String vipprice) {
		this.vipprice = vipprice;
	}

	public String getStockprice() {
		return stockprice;
	}

	public void setStockprice(String stockprice) {
		this.stockprice = stockprice;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	
	
	
}
