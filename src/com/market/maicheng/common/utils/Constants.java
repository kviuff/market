package com.market.maicheng.common.utils;

public class Constants {
	/**
	 * sessionID
	 */
	public static String LOGINED = "WEBADMIN_LOGIN_USERID";
	public static String LOGINED_MERCHANT = "WEBADMIN_LOGIN_USERID";
	
	public static String WEBLOGIN = "WEB_LOGIN_USERID";
	
	public static String JURI = "WEB_LOGIN_JURI";
	/**
	 * 查询返回失败
	 */
	public static int EXCEPTION_RESULT = 0;		
	/**
	 * 查询返回成功
	 */
	public static int SUCCESS_RESULT = 1;			
	/**
	 * 每页数量
	 */
	public static int PAGESIZE = 10;
	
	//用户相关api
	public static final String apiUrl = "http://api.fmart.com.cn:8082";
	/**
	 * 登陆api
	 * 参数?username={username}&password={password}
	 */
	public static final String apiLogin = "/api/UserManager/Login";
	
	public static final String apiToken = "/token";
}
