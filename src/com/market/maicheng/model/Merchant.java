package com.market.maicheng.model;

import java.util.List;

/**
 * 商户表
 * @author Shinobi
 *
 */
public class Merchant {
	private long id;
	/**
	 * 用户ID
	 */
	private long userid;
	/**
	 * 商家账号名
	 */
	private String accountName;

	/**
	 * 商铺名称
	 */
	private String shopName;
	/**
	 * 省ID
	 */
	private long provinceId;
	/**
	 * 省名称
	 */
	private String provinceName;
	/**
	 * 市id
	 */
	private long cityId;
	/**
	 * 市名称
	 */
	private String cityName;
	/**
	 * 地区ID
	 */
	private long regionid;
	/**
	 * 地区
	 */
	private String region;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 营业执照
	 */
	private String businessLicense;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 身份证号
	 */
	private String carid;
	/**
	 * 手持身份证照片
	 */
	private String caridPhoto;
	/**
	 * 申请时间
	 */
	private long createTime;
	/**
	 * 审核状态 0未审核 1已审核 2拒绝
	 */
	private int auditState;
	/**
	 * 审核时间
	 */
	private long auditTime;
	/**
	 * 审核人
	 */
	private long auditUserid;
	/**
	 * 是否删除 1删除
	 */
	private int isdel;
	/**
	 * 商户类型：0经销商 1代理商
	 */
	private int merchantType;
	/**
	 * 是否推荐 1推荐
	 */
	private int recommend;
	
	/******************************************************************************************/
	/*****************************************第二环节*****************************************/
	/******************************************************************************************/
	/**
	 * 商家分类
	 */
	private List<MerchantCategory> merchantClass;
	/**
	 * 店铺logo
	 */
	private String merchantLogo;
	/**
	 * 店铺头像
	 */
	private String merchantHead;
	/**
	 * 店铺地址
	 */
	private String merchantAddress;
	/**
	 * 店铺描述
	 */
	private String merchantDes;
	/**
	 * 打印备注
	 */
	private String printRemarks;
	
	/**
	 * 商店购物车数据
	 */
	private List<ShopCar> shopCarList;
	

	/**
	 * 是否公开价格
	 */
	private int publicPric;
	
	public int getPublicPric() {
		return publicPric;
	}
	public void setPublicPric(int publicPric) {
		this.publicPric = publicPric;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public long getRegionid() {
		return regionid;
	}
	public void setRegionid(long regionid) {
		this.regionid = regionid;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public String getCaridPhoto() {
		return caridPhoto;
	}
	public void setCaridPhoto(String caridPhoto) {
		this.caridPhoto = caridPhoto;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public int getAuditState() {
		return auditState;
	}
	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}
	public long getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(long auditTime) {
		this.auditTime = auditTime;
	}
	public long getAuditUserid() {
		return auditUserid;
	}
	public void setAuditUserid(long auditUserid) {
		this.auditUserid = auditUserid;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public int getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(int merchantType) {
		this.merchantType = merchantType;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public List<MerchantCategory> getMerchantClass() {
		return merchantClass;
	}
	public void setMerchantClass(List<MerchantCategory> merchantClass) {
		this.merchantClass = merchantClass;
	}
	public String getMerchantLogo() {
		return merchantLogo;
	}
	public void setMerchantLogo(String merchantLogo) {
		this.merchantLogo = merchantLogo;
	}
	public String getMerchantHead() {
		return merchantHead;
	}
	public void setMerchantHead(String merchantHead) {
		this.merchantHead = merchantHead;
	}
	public String getMerchantAddress() {
		return merchantAddress;
	}
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	public String getMerchantDes() {
		return merchantDes;
	}
	public void setMerchantDes(String merchantDes) {
		this.merchantDes = merchantDes;
	}
	public String getPrintRemarks() {
		return printRemarks;
	}
	public void setPrintRemarks(String printRemarks) {
		this.printRemarks = printRemarks;
	}
	public List<ShopCar> getShopCarList() {
		return shopCarList;
	}
	public void setShopCarList(List<ShopCar> shopCarList) {
		this.shopCarList = shopCarList;
	}
	
}
