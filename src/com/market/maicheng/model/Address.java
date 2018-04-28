package com.market.maicheng.model;

public class Address {
	
	private long id;
	private String ids;
	
	/**
	 * 收货人
	 */
	private String receiver;
	/**
	 * 性别 0男1女
	 */
	private int sex;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 省id
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
	 * 收货地址
	 */
	private String address;
	/**
	 * 是否默认 0否1是
	 */
	private int isdefault;
	/**
	 * 邮编
	 */
	private String zipcode;
	/**
	 * 地区ID
	 */
	private long regionid;
	/**
	 * 地区名字
	 */
	private String region;
	
	private long addtime;
	/**
	 * 用户ID
	 */
	private long userid;
	
	public long getAddtime() {
		return addtime;
	}
	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIds() {
		return String.valueOf(id);
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(int isdefault) {
		this.isdefault = isdefault;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
}
