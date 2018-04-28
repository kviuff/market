package com.market.maicheng.common.utils;

import javax.servlet.http.HttpServletRequest;


public class SessionUtils {
	/**
	 * 前台
	 * @param request
	 * @param name
	 */
	public static void setSessionWeb(HttpServletRequest request,String name){
		SessionUtils.setSession(request, Constants.WEBLOGIN, name);
	}
	/**
	 * 前台
	 * @param request
	 * @return
	 */
	public static long getSessionWeb(HttpServletRequest request){
		return SessionUtils.getSessionByName(request, Constants.WEBLOGIN);
	}
	/**
	 * 后台
	 * @param request
	 * @param name
	 */
	public static void setSessionWebAdmin(HttpServletRequest request,String name){
		SessionUtils.setSession(request, Constants.LOGINED, name);
	}
	/**
	 * 后台
	 * @param request
	 * @return
	 */
	public static long getSessionWebAdmin(HttpServletRequest request){
		return SessionUtils.getSessionByName(request, Constants.LOGINED);
	}
	public static void setSession(HttpServletRequest request,String key,String name){
		request.getSession().setAttribute(key,name);
	}
	
	
	
	/**
	 * 删除指定session---前台
	 * @param request
	 */
	public static void removeSessionWeb(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.WEBLOGIN);
	}
	
	

	/**
	 * 删除指定session---后台
	 * @param request
	 */
	public static void removeSessionWebAdmin(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.LOGINED);
	}
	
	
	
	public static long getSessionByName(HttpServletRequest request,String key){
		long relong = 0L;
		try{
			relong = Long.parseLong(request.getSession().getAttribute(key)+"");		
		}catch (Exception e) {
			// TODO: handle exception
			relong = 0L;
		}
		return relong;
	}
	
	public static String getSessionByName11(HttpServletRequest request,String key){
		String relong = "";
		try{
			relong = request.getSession().getAttribute(key)+"";		
		}catch (Exception e) {
			// TODO: handle exception
			relong = "";
		}
		return relong;
	}
}
