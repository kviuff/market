package com.market.maicheng.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	
	public static String getCookieByNameValue(HttpServletRequest request,String name){
		return CookieUtils.getCookieByName(request, name).getValue();
	}
	
	public static long getCookieByNameValueForLong(HttpServletRequest request,String name){
		long relong = 0L;
		try{
			relong = Long.parseLong(CookieUtils.getCookieByName(request,name).getValue());
		}catch (Exception e) {
			relong = 0L;
		}
		return relong;
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	/**
	 * 前台添加cookie
	 * @param mid
	 */
	public static void setcookie(HttpServletResponse response,String cookiename,String mid){
		
		Cookie cookie = new Cookie(cookiename,mid);
		cookie.setMaxAge(60*60*24*7);
		response.addCookie(cookie);
	}
	
	public static void removeVerCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("checkCode", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
}	

