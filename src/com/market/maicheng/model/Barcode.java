package com.market.maicheng.model;

import java.util.List;

/**
 * 条码库存表
 * @author Shinobi
 *
 */
public class Barcode {
	private long id;
	/**
	 * 商品名称
	 */
	private String title;
	/**
	 * 条形码ID
	 */
	private String barcodeid;
	/**
	 * 商品类别
	 */
	private long categoryID;
	/**
	 * 商品类别名称
	 */
	private String categoryName;

	/**
	 * 商品一级分类
	 */
	private int gcId1;
	/**
	 * 商品二级分类
	 */
	private int gcId2;
	/**
	 * 商品三级分类
	 */
	private int gcId3;
	/**
	 * 品牌
	 */
	private long brandid;
	/**
	 * 品名
	 */
	private String brandname;
	/**
	 * 系列
	 */
	private String series;
	/**
	 * 规格
	 */
	private String specs;
	/**
	 * 商品图片
	 */
	private String img;
	/**
	 * 添加时间
	 */
	private long createTime;
	/**
	 * 添加人
	 */
	private long createUserID;
	/**
	 * 是否删除 0未删除 1删除
	 */
	private int isDel;
	
	private List<BarcodePrice> priceList;
	
	
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
	public String getBarcodeid() {
		return barcodeid;
	}
	public void setBarcodeid(String barcodeid) {
		this.barcodeid = barcodeid;
	}
	public long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}
	public int getGcId1() {
		return gcId1;
	}
	public void setGcId1(int gcId1) {
		this.gcId1 = gcId1;
	}
	public int getGcId2() {
		return gcId2;
	}
	public void setGcId2(int gcId2) {
		this.gcId2 = gcId2;
	}
	public int getGcId3() {
		return gcId3;
	}
	public void setGcId3(int gcId3) {
		this.gcId3 = gcId3;
	}
	public long getBrandid() {
		return brandid;
	}
	public void setBrandid(long brandid) {
		this.brandid = brandid;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(long createUserID) {
		this.createUserID = createUserID;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public List<BarcodePrice> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<BarcodePrice> priceList) {
		this.priceList = priceList;
	}

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
