package com.market.maicheng.model;
/**
 * 品牌
 * @author 高红飞
 *
 */
public class Mbrand {


	private int id;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 品牌首字母
	 */
	private String brandInitial;
	/**
	 * 类别名称
	 */
	private String brandClass;
	/**
	 * 图片
	 */
	private String brandPic;
	/**
	 * 排序
	 */
	private int brandSort;
	/**
	 * 推荐，0为否，1为是，默认为0
	 */
	private int brandRecommend;
	/**
	 * 店铺id
	 */
	private int storeId;
	/**
	 * 品牌申请，0为申请中，1为通过，默认为1，申请功能是会员使用，系统后台默认为1
	 */
	private int brandApply;
	/**
	 * 所属分类id
	 */
	private int classId;
	/**
	 * 品牌展示类型 0表示图片 1表示文字 
	 */
	private int showType;
	/**
	 * 品牌背景标识
	 */
	private String brandBgpic;
	/**
	 * 品牌店铺推荐
	 */
	private int brandTjstore;
	/**
	 * 品牌详情介绍
	 */
	private String brandIntoduction;
	/**
	 * 是否被删除标记
	 */
	private int isdel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getBrandClass() {
		return brandClass;
	}
	public void setBrandClass(String brandClass) {
		this.brandClass = brandClass;
	}
	public String getBrandPic() {
		return brandPic;
	}
	public void setBrandPic(String brandPic) {
		this.brandPic = brandPic;
	}
	public int getBrandSort() {
		return brandSort;
	}
	public void setBrandSort(int brandSort) {
		this.brandSort = brandSort;
	}
	public int getBrandRecommend() {
		return brandRecommend;
	}
	public void setBrandRecommend(int brandRecommend) {
		this.brandRecommend = brandRecommend;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getBrandApply() {
		return brandApply;
	}
	public void setBrandApply(int brandApply) {
		this.brandApply = brandApply;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public String getBrandBgpic() {
		return brandBgpic;
	}
	public void setBrandBgpic(String brandBgpic) {
		this.brandBgpic = brandBgpic;
	}
	public int getBrandTjstore() {
		return brandTjstore;
	}
	public void setBrandTjstore(int brandTjstore) {
		this.brandTjstore = brandTjstore;
	}
	public String getBrandIntoduction() {
		return brandIntoduction;
	}
	public void setBrandIntoduction(String brandIntoduction) {
		this.brandIntoduction = brandIntoduction;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
}
