package com.market.maicheng.controllers.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.model.Member;
import com.market.maicheng.service.MemberService;
import com.wechat.common.WeChatConstat;
import com.wechat.model.ReceiveXmlEntity;
import com.wechat.model.ReceiveXmlProcess;
import com.wechat.utils.CheckoutUtil;
import com.wechat.utils.HttpUtils;

@Controller
@RequestMapping(value = "/wechat")
public class WechatController extends BaseController {

	@Autowired
	private MemberService memberService;
	
	/**
	 * 微信消息接收和token验证
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/checkToken")
	public void checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		System.out.println("请求方式=======" + isGet);
		PrintWriter print;
		if (isGet) {
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
				try {
					print = response.getWriter();
					print.write(echostr);
					print.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			// 获取openId
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
			String openId = e.getFromUserName();
			
			// 获取access_token
			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WeChatConstat.APP_ID + "&secret=" + WeChatConstat.APP_SECRET;
			String result = HttpUtils.doGet(access_token_url);
			JSONObject jsonObject = JSON.parseObject(result);
			String errorCode = jsonObject.getString("errcode");
			if (StringUtils.isEmpty(errorCode)) { // 返回编码为空表示请求成功
				String accessToken = jsonObject.getString("access_token");
				// 获取用户信息
				String userInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
				String userInfoResult = HttpUtils.doGet(userInfo_url);
				JSONObject userInfoJson = JSON.parseObject(userInfoResult);
				errorCode = jsonObject.getString("errcode");
				if (StringUtils.isEmpty(errorCode)) {
					String nickName = userInfoJson.getString("nickname");
					Member member = memberService.getMemberByWechat(nickName);
					if (null != member) {
						if (StringUtils.isEmpty(member.getOpenID())) {// 如果没有绑定过openID,更新一下用户的openID
							member.setOpenID(openId);
							memberService.updateMember(member);
						}
						//System.out.println("openId====" + openId);
						//System.out.println("accessToken====" + accessToken);
						//System.out.println("nickname====" + nickName);
					}
				} else {
					System.out.println("userInfo_errorcode===" + errorCode);
				}
			} else {
				System.out.println("access_token_errorcode===" + errorCode);
			}
		}
	}
	
	/**
	 * 获取用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "weixinUserInfo")
	public void weixinUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// 授权成功后获取code
			String code = request.getParameter("code");
			// 根据code获取access_token
			String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeChatConstat.APP_ID 
					+ "&secret=" + WeChatConstat.APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
			
	        // 获取返回信息
	        String strResult = HttpUtils.doGet(accessTokenUrl);
	        JSONObject jsonObject = JSON.parseObject(strResult);
	        String errorCode = jsonObject.getString("errcode");
	        
	        if(StringUtils.isEmpty(errorCode)){ // 无errorcode表示正常返回
	        		String accessToken = jsonObject.getString("access_token");
	        		String openId = jsonObject.getString("openid");
	        		// 获取用户信息
				String userInfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
				String userInfoResult = HttpUtils.doGet(userInfo_url);
				JSONObject userInfoJson = JSON.parseObject(userInfoResult);
				errorCode = jsonObject.getString("errcode");
				if (StringUtils.isEmpty(errorCode)) {
					String nickName = userInfoJson.getString("nickname");
					System.out.println("openId====" + openId);
					System.out.println("accessToken====" + accessToken);
					System.out.println("nickname====" + nickName);
					Member member = memberService.getMemberByWechat(nickName);
					if (null != member) {
						if (StringUtils.isEmpty(member.getOpenID())) {// 如果没有绑定过openID,更新一下用户的openID
							member.setOpenID(openId);
							memberService.updateMember(member);
							System.out.println("授权成功===");
						}
					} else {
						System.out.println("member====空");
					}
				} else {
					System.out.println("userInfo_errorcode===" + errorCode);
				}
	        }else{
	        		System.out.println("获取access_token出错====");
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
