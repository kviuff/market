package com.market.maicheng.controllers.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.market.maicheng.model.ReceiveXmlEntity;
import com.market.maicheng.model.ReceiveXmlProcess;

@Controller
@RequestMapping(value = "/wechat")
public class WechatController extends BaseController {
	
	
	@RequestMapping(value = "/getWechatInfo") 
	@ResponseBody
    public void getWechatInfo(HttpServletRequest request) {
		try {
			InputStream input = request.getInputStream();
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			BufferedReader bufferReader = new BufferedReader(reader);
			String s = "";
			StringBuffer sb = new StringBuffer();
			while ((s = bufferReader.readLine()) != null) {
				sb.append(s);
			}
			String xml = sb.toString();
			ReceiveXmlEntity e = new ReceiveXmlProcess().getMsgEntity(xml);
			System.out.println("xml===" + xml);
			System.out.println("toUsername===" + e.getToUserName());
			System.out.println("FromUserName===" + e.getFromUserName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
