package com.market.maicheng.common.utils;

public class ByteUtils {
	private static String hexStr =  "0123456789ABCDEF";  //全局
	/**
	 * byte转String
	 * @param bytes
	 * @return
	 */
	public static String BinaryToHexString(byte[] bytes){     
        
        String result = "";     
        String hex = "";     
        for(int i=0;i<bytes.length;i++){     
            //字节高4位     
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4)); 
            //字节低4位     
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));   
            result +=hex;     
        }     
        return result;     
    }
	
	/**
	 * byte转int
	 * @param res
	 * @return
	 */
	public static int byte2int(byte[] res) {   
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或   
		| ((res[2] << 24) >>> 8) | (res[3] << 24);   
		return targets;   
	} 
	

	/** 
     * 将低字节数组转换为int 
     *  
     * @param b 
     *            byte[] 
     * @return int 
     */  
    public static int lBytesToInt(byte[] b) {  
        int s = 0;  
        for (int i = 0; i < 3; i++) {  
            if (b[3 - i] >= 0) {  
                s = s + b[3 - i];  
            } else {  
                s = s + 256 + b[3 - i];  
            }  
            s = s * 256;  
        }  
        if (b[0] >= 0) {  
            s = s + b[0];  
        } else {  
            s = s + 256 + b[0];  
        }  
        return s;  
    }
    
    /**
     * 小端byte转long
     * @param b
     * @return
     */
    public static long lowBytes2long(byte[] buffer) {  
    	long  values = 0;   
        for (int i = 7; i >= 0; i--) {    
            values <<= 8; values|= (buffer[i] & 0xff);   
        }   
        return values;  
    }  
    /**
     * long转byte
     * @param x
     * @return
     */
    public static byte[] longToBytes(long x) {    
    	byte[] buffer = new byte[8]; 
        for (int i = 0; i < 8; i++) {   
             int offset = 64 - (i + 1) * 8;    
             buffer[i] = (byte) ((x >> offset) & 0xff); 
         }
        return buffer;    
    }    
    
    /** 
     * 字节转换为浮点 
     *  
     * @param b 字节（至少4个字节） 
     * @param index 开始位置 
     * @return 
     */  
    public static float byte2float(byte[] b, int index) {    
        int l;                                             
        l = b[index + 0];                                  
        l &= 0xff;                                         
        l |= ((long) b[index + 1] << 8);                   
        l &= 0xffff;                                       
        l |= ((long) b[index + 2] << 16);                  
        l &= 0xffffff;                                     
        l |= ((long) b[index + 3] << 24);                  
        return Float.intBitsToFloat(l);                    
    }  
}
