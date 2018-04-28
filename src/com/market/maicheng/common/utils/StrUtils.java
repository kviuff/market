package com.market.maicheng.common.utils;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.util.Base64;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class StrUtils {
	public static final String DEFAULT_STRING = "";
	public static final int DEFAULT_INT = 0;
	public static final long DEFAULT_LONG = 0l;
	public static final double DEFAULT_DOUBLE = 0.00;
	
	public static String getString(HttpServletRequest request, String s) {
		return getString(request, s, DEFAULT_STRING); 
	}
	
	
	public static String getPinyinString(String ChineseLanguage){
	    char[] cl_chars = ChineseLanguage.trim().toCharArray();
	    String hanyupinyin = "";
	    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
	    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部大写
	    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
	    try {
	        for (int i = 0; i < cl_chars.length; i++) {
	            String str = String.valueOf(cl_chars[i]);
	            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
	                hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(
	                        cl_chars[i], defaultFormat)[0];
	            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
	                hanyupinyin += cl_chars[i];
	            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

	                hanyupinyin += cl_chars[i];
	            } else {// 否则不转换
	            }
	        }
	    } catch (BadHanyuPinyinOutputFormatCombination e) {
	        System.out.println("字符不能转成汉语拼音");
	    }
	    return hanyupinyin;
	}
	
	public static String getPinyinA(String ChineseLanguage){
		String pinyin = getPinyinString(ChineseLanguage);
		if(!"".equals(pinyin)){
			return pinyin.substring(0, 1);
		}
		return "";
	}
	
	public static String getString(HttpServletRequest request, String s,
			String defaultString) {
		String s1 = "";
		try{
			s1 = request.getParameter(s);
			if(s1 != null && !"".equals(s1) && isMessyCode(s1)){
				s1 = new String(s1.getBytes("iso8859-1"),"UTF-8");
			}
			//s1 = new String(s1.getBytes("iso8859-1"),"UTF-8");
			if (StringUtils.isBlank(s1)){
				return defaultString;
			}
		}catch (Exception e) {
			s1 = defaultString;
		}
		return XssUtil.xssChange(s1);
	}
	
	public static int getInt(HttpServletRequest request, String s){
		return getInt(request, s, DEFAULT_INT);
	}
	
	public static int getInt(HttpServletRequest request, String s,
			int defaultInt) {
		try {
			String temp = getString(request, s);
			if (StringUtils.isBlank(temp))
				return defaultInt;
			else
				return Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static long getLong(HttpServletRequest request, String s,
			long defaultLong) {
		try {
			String temp = getString(request, s);
			if (StringUtils.isBlank(temp))
				return defaultLong;
			else
				return Long.parseLong(temp);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static long getLong(HttpServletRequest request, String s){
		return getLong(request, s, DEFAULT_LONG);
	}
	
	public static double getDouble(HttpServletRequest request, String s,
			double defaultLong) {
		try {
			String temp = getString(request, s);
			if (StringUtils.isBlank(temp))
				return defaultLong;
			else
				return Double.parseDouble(temp);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	public static double getDouble(HttpServletRequest request, String s){
		return getDouble(request, s, DEFAULT_DOUBLE);
	}
	
	/**
	 * 获取IP
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String s1 = request.getRemoteAddr();
		if (s1 == null)
			return "";
		return s1;
	}
	
    //获取MAC地址的方法  
    private static String getMACAddress(InetAddress ia)throws Exception{  
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();  
          
        //下面代码是把mac地址拼装成String  
        StringBuffer sb = new StringBuffer();  
          
        for(int i=0;i<mac.length;i++){  
            if(i!=0){  
                sb.append("-");  
            }  
            //mac[i] & 0xFF 是为了把byte转化为正整数  
            String s = Integer.toHexString(mac[i] & 0xFF);  
            sb.append(s.length()==1?0+s:s);  
        }  
          
        //把字符串所有小写字母改为大写成为正规的mac地址并返回  
        return sb.toString().toUpperCase();  
    }  
    
    public static String getMAC() {
    	String mac = null;         
    	try {
    		Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");
    		InputStream is = pro.getInputStream();
    		BufferedReader br = new BufferedReader(new InputStreamReader(is));
    		String message = br.readLine();
    		int index = -1;
    		while (message != null) {                 
    			if ((index = message.indexOf("Physical Address")) > 0) {                     
    				mac = message.substring(index + 36).trim();                     
    				break;                 
    			}                 
    			message = br.readLine();
    		}
    		br.close();
    		pro.destroy();
    		} catch (IOException e) {
    			System.out.println("Can't get mac address!");
    			return null;
    			}         
    	return mac;     
    } 
    
    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
    	 try {
             Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
             Matcher m = p.matcher(strName);
             String after = m.replaceAll("");
             String temp = after.replaceAll("\\p{P}", "");
             char[] ch = temp.trim().toCharArray();

             int length = (ch != null) ? ch.length : 0;
             for (int i = 0; i < length; i++) {
                 char c = ch[i];
                 if (!Character.isLetterOrDigit(c)) {
                     String str = "" + ch[i];
                     if (!str.matches("[\u4e00-\u9fa5]+")) {
                         return true;
                     }
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

         return false;
 
    }
    
    /*解密为加密的逆过程 
    */  
    public static String decodeValue(String value){  
        if(value.equals("")){  
            throw new NullPointerException();  
        }  
        if(value.length()<20){  
            throw new NullPointerException();  
        }  
        String charLength=value.substring(3, 5);//加密后的字符有多少位  
        int charLen=Integer.parseInt(charLength,16);//转换成10进制  
        int type=Integer.parseInt(value.substring(5, 6));//加密字符的类型（0：数字，1：字符串）  
        String valueEnc=value.substring(6, 6+charLen);//16进制字符串  
        if(type==0){  
            int trueValue=Integer.parseInt(valueEnc,16);  
            return String.valueOf(trueValue);  
        }else{  
            StringBuffer sb=new StringBuffer();  
            String[] valueEncArray=valueEnc.split("");
            for(int i=1;i<valueEncArray.length;i+=2){  
                int value10=Integer.parseInt(valueEncArray[i]+valueEncArray[i+1],16);//转换成10进制的asc码  
                sb.append(String.valueOf((char)value10));//asc码转换成字符  
            }  
            return sb.toString();  
        }  
    }
    
    private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
        60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
        -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
        38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
        -1, -1 };
    
    /**
     * 解密
     * 
     * @param str
     * @return
     */
    public static String decode(String str) {
        byte[] data = str.getBytes();
        int len = data.length;
        ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
        int i = 0;
        int b1, b2, b3, b4;

        while (i < len) {
            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1) {
                break;
            }

            do {
                b2 = base64DecodeChars[data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1) {
                break;
            }
            buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

            do {
                b3 = data[i++];
                if (b3 == 61) {
                    return new String(buf.toByteArray());
                }
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1) {
                break;
            }
            buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

            do {
                b4 = data[i++];
                if (b4 == 61) {
                    return new String(buf.toByteArray());
                }
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1) {
                break;
            }
            buf.write((int) (((b3 & 0x03) << 6) | b4));
        }
        return new String(buf.toByteArray());
    }
    
    public static int setStringToInt(String str){
    	int reint = 0;
    	try{
    		reint = Integer.parseInt(str);
    	}catch (Exception e) {
    		reint = 0;
		}
    	return reint;
    }
    
    /**
     * 对给定的字符串进行base64加密操作
     */
    public static String encodeData(String inputData) {
    	//sun.misc.BASE64Encoder().encode(inputData)

        return null;
    }
    
    public static void main(String[] arguments) throws Exception{
    	//String str = "2428c17069643d323031363131323830313334323338303137266964733d3230313631313238303139363433323533322c323031363131323830313731323331353834266e756d3d31";
        String str = "cGlkPTIwMTYxMTI4MDEzNDIzODAxNyZpZHM9MjAxNjExMjgwMTk2NDMyNTMyLDIwMTYxMTI4MDE3MTIzMTU4NCZudW09MQ==";
    	System.out.println(StrUtils.decodeValue(str)); 
        
        //productid=201709140397147767&barcodepriceid=201709140342431356&count=1&price=15&type=1
        
    }  
    
}
