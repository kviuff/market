package com.market.maicheng.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;

/**
 * 文件操作类
 * @author Snake
 *
 */
public class FileUtils {
	/**
	 * 读取文件
	 * @param path
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String readFile(String path){
		path = URLDecoder.decode(this.getClass().getResource(path).getPath());
		File file = new File(path);
		StringBuffer sb = new StringBuffer();
        try {
        	InputStreamReader insReader = new InputStreamReader(new FileInputStream(file) ,"UTF-8");
        	BufferedReader bufReader = new BufferedReader(insReader);
        	String line = new String();
        	while ((line = bufReader.readLine()) != null ) {
        		sb.append(line+"\n");
			}
        	bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
	}
	
	/**
	 * 写入文件
	 * @param path	路径
	 * @param content 内容
	 * @param isappend 是否追加
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean writeFile(String path,String content,boolean isappend){
		boolean flag = false;
		path = URLDecoder.decode(this.getClass().getResource(path).getPath());
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(path),"UTF-8");
            out.write(content);
            out.flush();
            out.close();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 删除文件
	 * @param sPath
	 * @return
	 */
	public static int deleteFile(String sPath) {  
	    int flag = 0;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = 1;  
	    }  
	    return flag;  
	} 
	
	public static void main(String[] a){
		FileUtils fu = new FileUtils();
		//fu.writeFile("/education.txt", "woc1111");
		System.out.println(fu.readFile("/education.txt"));
	}
}
