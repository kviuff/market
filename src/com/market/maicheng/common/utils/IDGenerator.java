package com.market.maicheng.common.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * 生成唯一ID
 * @author Snake
 *
 */
public final class IDGenerator {
	private static int ID_LEN = 8;
//	private static long index = 1;
    private static IDGenerator generator = new IDGenerator();
    protected static Format fmat = new SimpleDateFormat("yyyyMMddhh");
 
    /**
     * private and only constructor
     */
    private IDGenerator() {
    }
 
    /**
     * @return Generator
     */
    public static IDGenerator getInstance() {
        if (generator == null)
            generator = new IDGenerator();
        return generator;
    }
 
    /**
     * @return the iD
     * 线下测试自动生成ID
     */
    public static synchronized  long getID() {
        int root = (int) Math.pow(10, ID_LEN);
        int id = 0;
        do {
            long tmp = Math.abs(Double.doubleToLongBits(Math.random()));
            id = (int) (tmp % root);
        } while (id < (root / 10));
        Date date = new Date();
        return Long.parseLong(fmat.format(date) + "" +  id);
    }
    
    /**
     * 获取后台用户登录ID
     * @param request
     * @return
     */
    public static long getLoginID(HttpServletRequest request){
    	//return SessionUtils.getSessionWebAdmin(request);
    	return CookieUtils.getCookieByNameValueForLong(request, Constants.LOGINED);
    }
    
    /**
     * 获取后台商户用户登录ID
     * @param request
     * @return
     */
    public static long getMerchantLoginID(HttpServletRequest request){
    	//return SessionUtils.getSessionWebAdmin(request);
    	return CookieUtils.getCookieByNameValueForLong(request, Constants.LOGINED_MERCHANT);
    }
    
    /**
     * 获取前台用户登录ID(cookie or session)
     * @param request
     * @return
     */
    public static long getWebLoginID(HttpServletRequest request){
    	long id = 0;
    	if(SessionUtils.getSessionWeb(request)>0){
    		id = SessionUtils.getSessionWeb(request);
    	}else if(CookieUtils.getCookieByNameValueForLong(request, Constants.WEBLOGIN)>0){
    		id = CookieUtils.getCookieByNameValueForLong(request, Constants.WEBLOGIN);
    	}
    	return id;
    }
    
    
    /**
     * 需要vpn 线上必须使用的id获取方式
     * @return
     */
    /*public static long getOrderID(){
    	DefaultOrderidServiceImpl defaultOrderIdService = DefaultOrderidServiceImpl.getInstance(); 
		String order_id = defaultOrderIdService.nextId();
		return Long.parseLong(order_id);
    }*/
 
    /**
     * @return the iD_LEN
     */
    public static int getID_LEN() {
        return ID_LEN;
    }
 
    /**
     * @param iD_LEN
     *            the iD_LEN to set
     */
    public static void setID_LEN(int iD_LEN) {
        ID_LEN = iD_LEN;
    }
    
    public static void main(String[] args){
    	System.out.println(getID());
    }
}
