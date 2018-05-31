package com.wechat.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtils {

	public static String doGet(String url) {
		CloseableHttpClient httpCilent2 = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接超时时间
				.setConnectionRequestTimeout(5000) // 设置请求超时时间
				.setSocketTimeout(5000).setRedirectsEnabled(true)// 默认允许自动重定向
				.build();
		HttpGet httpGet2 = new HttpGet(url);
		httpGet2.setConfig(requestConfig);
		String srtResult = "";
		try {
			HttpResponse httpResponse = httpCilent2.execute(httpGet2);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				srtResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
				//System.out.println(srtResult);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpCilent2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return srtResult;
	}
	
	public static void main(String[] args) {
		String result = doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx00859ea61ad39301&secret=621f7264072c8b6bd83a3fcd6170f2a8");
		JSONObject jsonObject = JSON.parseObject(result);
		System.out.println(jsonObject.getString("access_token"));
		
		String userInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + jsonObject.getString("access_token") + "&openid=o5rpjxNlot7xA6r63rvVUNjBoTB4&lang=zh_CN";
		JSONObject jsonObject11 = JSON.parseObject(doGet(userInfo_url));
		System.out.println(jsonObject11.getString("nickname"));
	}

}
