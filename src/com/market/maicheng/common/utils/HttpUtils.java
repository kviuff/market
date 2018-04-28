package com.market.maicheng.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HttpUtils {
	/**
	 * http请求get方法
	 * @param urlPath
	 * @return
	 */
	public static String doGet(String urlPath){
		StringBuilder sb = new StringBuilder();
		try{
			URL url = new URL(urlPath.trim());
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        int state = connection.getResponseCode();
	        if (state != 200) {
	            return "";
	        }
	        connection.connect();
	        InputStream inputStream = connection.getInputStream();
	        Reader reader = new InputStreamReader(inputStream, "UTF-8");
	        BufferedReader bufferedReader = new BufferedReader(reader);
	        String str = null;
	        
	        while ((str = bufferedReader.readLine()) != null) {
	            sb.append(str);
	        }
	        reader.close();
	        connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return sb.toString();
	}
	
	/**
	 * http请求post方法
	 * @param urlPath
	 * @param param
	 * @return
	 *//*
	@SuppressWarnings("deprecation")
	public static String doPost(String urlPath,String param,String token){
		HttpClient client = new DefaultHttpClient(); 
		System.out.println("urlPath="+urlPath);
        HttpPost post = new HttpPost(urlPath);
        StringBuilder strber = new StringBuilder();
        post.setHeader("Content-Type", "application/json");  
        
        try {
			StringEntity s = new StringEntity(param,"utf-8");    
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(s);
            if(token != null){
            	post.addHeader("Authorization","Bearer "+token);
            }
            HttpResponse httpResponse = client.execute(post);
            
            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            	InputStream inStream = httpResponse.getEntity().getContent();  
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"utf-8"));
                String line = null;    
                while ((line = reader.readLine()) != null){
                	strber.append(line);
                }                       
                inStream.close();
            }
        }catch (Exception e) {    
            throw new RuntimeException(e);    
        } 
        return strber.toString();
	}*/
}
