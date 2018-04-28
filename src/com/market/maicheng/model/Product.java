package com.market.maicheng.model;

import java.util.List;

/**
 * 产品表
 * @author Administrator
 *
 */
public class Product {
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
	 * 内容图片
	 */
	private String contentImg;
	/**
	 * 创建日期
	 */
	private long createTime;
	/**
	 * 商户ID
	 */
	private long merchantID;
	/**
	 * 是否删除 1 删除
	 */
	private int isdel;
	/**
	 * 状态 0:发布未审核1:审核通过上架 2:审核否决退回 3下架
	 */
	private int state;
	/**
	 * 审核人
	 */
	private long reviewedID;
	/**
	 * 审核时间
	 */
	private long reviewedTime;
	/**
	 * 状态 0:无码1:有码
	 */
	private int hasBarCode;
	/**
	 * 是否置顶 1： 置顶 ，0：不置顶
	 */
	private int istop;
	
	/**
	 * 属性价格列表
	 */
	private List<BarcodePrice> barcodePriceList;
	
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
	public String getContentImg() {
		return contentImg;
	}
	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(long merchantID) {
		this.merchantID = merchantID;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getReviewedID() {
		return reviewedID;
	}
	public void setReviewedID(long reviewedID) {
		this.reviewedID = reviewedID;
	}
	public long getReviewedTime() {
		return reviewedTime;
	}
	public void setReviewedTime(long reviewedTime) {
		this.reviewedTime = reviewedTime;
	}
	public int getHasBarCode() {
		return hasBarCode;
	}
	public void setHasBarCode(int hasBarCode) {
		this.hasBarCode = hasBarCode;
	}
	public int getIstop() {
		return istop;
	}
	public void setIstop(int istop) {
		this.istop = istop;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<BarcodePrice> getBarcodePriceList() {
		return barcodePriceList;
	}
	public void setBarcodePriceList(List<BarcodePrice> barcodePriceList) {
		this.barcodePriceList = barcodePriceList;
	}
	
}
