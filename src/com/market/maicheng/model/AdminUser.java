package com.market.maicheng.model;

/**
 * 后台会员
 * @author Shinobi
 *
 */
public class AdminUser {
	private long adminuserid;
	/**
	 * 登录名
	 */
	private String adminusername;
	/**
	 * 登錄密码
	 */
	private String adminuserpass;
	/**
	 * 用戶权限
	 */
	private String adminuserrole;
	/**
	 * 創建日期
	 */
	private long createtime;
	/**
	 * 創建人
	 */
	private long createuser;
	/**
	 * 是否锁定 0 非锁定 1锁定
	 */
	private int unlock;
	/**
	 * 是否删除 0非删除 1删除
	 */
	private int isdel;
	/**
	 * 上次登錄時間
	 */
	private long logintime;
	/**
	 * 城市管理ID
	 */
	private long cityid;
	/**
	 * 管理城市名称
	 */
	private String cityName;
	
	private String jname;
	
	private String jid;
	
	public String getJid() {
		return jid;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public String getJname() {
		return jname;
	}
	public void setJname(String jname) {
		this.jname = jname;
	}
	public String getJcontent() {
		return jcontent;
	}
	public void setJcontent(String jcontent) {
		this.jcontent = jcontent;
	}
	private String jcontent;
	
	public long getAdminuserid() {
		return adminuserid;
	}
	public void setAdminuserid(long adminuserid) {
		this.adminuserid = adminuserid;
	}
	public String getAdminusername() {
		return adminusername;
	}
	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}
	public String getAdminuserpass() {
		return adminuserpass;
	}
	public void setAdminuserpass(String adminuserpass) {
		this.adminuserpass = adminuserpass;
	}
	public String getAdminuserrole() {
		return adminuserrole;
	}
	public void setAdminuserrole(String adminuserrole) {
		this.adminuserrole = adminuserrole;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	public long getCreateuser() {
		return createuser;
	}
	public void setCreateuser(long createuser) {
		this.createuser = createuser;
	}
	public long getLogintime() {
		return logintime;
	}
	public void setLogintime(long logintime) {
		this.logintime = logintime;
	}
	public int getUnlock() {
		return unlock;
	}
	public void setUnlock(int unlock) {
		this.unlock = unlock;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public long getCityid() {
		return cityid;
	}
	public void setCityid(long cityid) {
		this.cityid = cityid;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
