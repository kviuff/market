package com.market.maicheng.model;

/**
 * 用户实体
 * @author Shinobi
 *
 */
public class Member {
	private long id;
	/**
	 * 登陆用户名
	 */
	private String userName;
	/**
	 * 登陆密码
	 */
	private String password;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 联系方式
	 */
	private String mobile;
	/**
	 * 用户类型
	 */
	private int userType;
	/**
	 * 注册时间
	 */
	private long createTime;
	/**
	 * 上次登陆时间
	 */
	private long loginTime;
	/**
	 * 微信号
	 */
	private String wechatID;
	/**
	 * 是否删除 1 删除
	 */
	private int isdel;
	/**
	 * 用户头像
	 */
	private String headpic;
	/**
	 * 商户ID
	 */
	private long merchantid;
	/**
	 * 当前状态 0在职 1离职
	 */
	private int state;
	
	/**
	 * 微信号
	 */
	private String storeName;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public String getWechatID() {
		return wechatID;
	}
	public void setWechatID(String wechatID) {
		this.wechatID = wechatID;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	public long getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(long merchantid) {
		this.merchantid = merchantid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
