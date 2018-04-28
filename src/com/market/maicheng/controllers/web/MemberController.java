package com.market.maicheng.controllers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.Md5;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Member;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;

@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController{
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist", method = {RequestMethod.GET,RequestMethod.POST})  
	public String regist(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		//获取参数
		String username = StrUtils.getString(request, "username","");
		String password = StrUtils.getString(request, "password","");
		String realname = StrUtils.getString(request, "realname");
		String mobile = StrUtils.getString(request, "mobile");
		String wechatID = StrUtils.getString(request, "wechatID");
		String storeName = StrUtils.getString(request, "storeName");
		int userType = StrUtils.getInt(request, "usertype", 0);
		long merchantid = StrUtils.getLong(request, "merchant", 0);
		//封装member实体
		Member member = new Member();
		member.setId(IDGenerator.getID());
		member.setUserName(username);
		member.setPassword(Md5.md5(password));
		member.setRealName(realname);
		member.setMobile(mobile);
		member.setUserType(userType);
		member.setCreateTime(new Date().getTime());
		member.setWechatID(wechatID);
		member.setLoginTime(0L);
		member.setMerchantid(merchantid);
		member.setStoreName(storeName);
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			//添加用户
			if(username.length() == 0 || password.length() == 0){
				jsonObject.put("state", "3");
				jsonObject.put("result", "用户名密码为空");
			}else if(memberService.getMemberForUserName(username) != null){
				jsonObject.put("state", "4");
				jsonObject.put("result", "用户名已注册");
			}else{
				int state = memberService.addMember(member);
				if(state == 1){
					jsonObject.put("state", "1");
					jsonObject.put("result", "注册成功");
					jsonObject.put("data", member.getId());
				}else{
					jsonObject.put("state", "2");
					jsonObject.put("result", "注册失败");
				}
			}
			out.print(jsonObject.toString());
			out.close();
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 获取用户信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMember", method = {RequestMethod.GET,RequestMethod.POST})  
	public String getMember(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		long id = StrUtils.getLong(request, "userid");
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			out = response.getWriter();
			Member member = memberService.getMemberForid(id);
			if(member != null){
				jsonObject.put("state", "1");
				jsonObject.put("result", "ok");
				JSONObject json = new JSONObject();
				json.put("userid", member.getId());
				json.put("username", member.getUserName());
				json.put("realname", member.getRealName());
				json.put("mobile", member.getMobile());
				json.put("wechatID", member.getWechatID());
				json.put("userType", member.getUserType());		//userType 用户类型 0 普通用户 1 商家用户
				json.put("headpic", member.getHeadpic());
				json.put("storeName", member.getStoreName());
				if(member.getUserType() != 0){
					Merchant m = merchantService.getMerchantByUserid(member.getId());
					if(m != null){
						if(m.getAuditState() == 0){
							json.put("userType", 5);	//未审核商户
						}else if(m.getAuditState() == 2){
							json.put("userType", 6);	//审核拒绝商户
						}else if(m.getAuditState() == 1){
							if(m.getMerchantType() == 0){
								json.put("userType", 7);	//经销商
							}else{
								json.put("userType", 8);	//代理商
							}
							json.put("merchantID", m.getId());
						}
					}
				}
				if(member.getMerchantid() != 0){

					Merchant m = merchantService.getMerchantByid(member.getMerchantid());
					if(m.getUserid()!=member.getId()){
						json.put("userType", 9);	//员工账号
					}
					json.put("merchantID", member.getMerchantid());
					json.put("merchantUserID", m.getUserid());
				}
				jsonObject.put("data", json);
			}else{
				jsonObject.put("state", "2");
				jsonObject.put("result", "用户不存在");
			}
			out.print(jsonObject.toString());
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 获取用户信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMemberForUsername", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject getMemberForUsername(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		String username = StrUtils.getString(request, "username");
		
		JSONObject jsonObject = new JSONObject();
			Member member = memberService.getMemberForUserName(username);
			if(member != null){
				jsonObject.put("state", "1");
				jsonObject.put("result", "ok");
				JSONObject json = new JSONObject();
				json.put("userid", member.getId());
				json.put("username", member.getUserName());
				json.put("realname", member.getRealName());
				json.put("mobile", member.getMobile());
				json.put("wechatID", member.getWechatID());
				json.put("userType", member.getUserType());
				json.put("headpic", member.getHeadpic());
				json.put("storeName", member.getStoreName());
				if(member.getUserType() != 0){
					Merchant m = merchantService.getMerchantByUserid(member.getId());
					if(m != null){
						json.put("merchantID", m.getId());
					}
				}
				jsonObject.put("data", json);
			}else{
				jsonObject.put("state", "2");
				jsonObject.put("result", "用户不存在");
			}
		return jsonObject;
	}
	
	/**
	 * 用户登陆
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})  
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		String username = StrUtils.getString(request, "username");
		String password = StrUtils.getString(request, "password");
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			out = response.getWriter();
			Member member = memberService.getMemberForUserName(username);
			if(member != null){
				if(member.getPassword().equals(Md5.md5(password))){
					jsonObject.put("state", "1");
					jsonObject.put("result", "ok");
					jsonObject.put("data", member.getId());
					//更新登陆时间
					member.setLoginTime(new Date().getTime());
					memberService.updateMember(member);
				}else{
					jsonObject.put("state", "2");
					jsonObject.put("result", "密码错误");
				}
				
			}else{
				jsonObject.put("state", "2");
				jsonObject.put("result", "用户不存在");
			}
			out.print(jsonObject.toString());
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/updateMember", method = {RequestMethod.GET,RequestMethod.POST})  
	public String updateMember(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		//获取参数
		long userid = StrUtils.getLong(request, "userid");
		String password = StrUtils.getString(request, "password",null);
		String realname = StrUtils.getString(request, "realname");
		String mobile = StrUtils.getString(request, "mobile");
		String wechatID = StrUtils.getString(request, "wechatID");
		String storeName = StrUtils.getString(request, "storeName");
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			out = response.getWriter();
			Member member =  memberService.getMemberForid(userid);
			if(member != null){
				if(password != null){
					member.setPassword(Md5.md5(password));
				}
				member.setRealName(realname);
				member.setMobile(mobile);
				member.setWechatID(wechatID);
				member.setStoreName(storeName);
				if(memberService.updateMember(member) == 1){
					jsonObject.put("state", "1");
					jsonObject.put("result", "用户更新成功");
				}else{
					jsonObject.put("state", "3");
					jsonObject.put("result", "用户更新失败");
				}
			}else{
				jsonObject.put("state", "2");
				jsonObject.put("result", "用户不存在");
			}
			out.print(jsonObject.toString());
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		
		return null;
	}
	
	@RequestMapping(value = "/uploadhead", method = {RequestMethod.GET,RequestMethod.POST})  
	public String uploadhead(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		String headpic = StrUtils.getString(request, "headpic");
		long userid = StrUtils.getLong(request, "userid");
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			Member member =  memberService.getMemberForid(userid);
			if(member != null){
				member.setHeadpic(headpic);
				if(memberService.updateMember(member) == 1){
					jsonObject.put("state", "1");
					jsonObject.put("result", "头像更新成功");
				}else{
					jsonObject.put("state", "3");
					jsonObject.put("result", "头像更新失败");
				}
			}else{
				jsonObject.put("state", "2");
				jsonObject.put("result", "用户登陆失效");
			}
			out.print(jsonObject.toString());
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getMemberByMerchantid", method = {RequestMethod.GET,RequestMethod.POST})  
	public String getMemberByMerchantid(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		long merchantid = StrUtils.getLong(request, "merchantid" ,0);
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			List<Member> mlist = memberService.getMemberListByMerchantid(merchantid);
			JSONArray jsonlist = new JSONArray();
			JSONObject json = null;
			for(Member m : mlist){
				json = new JSONObject();
				json.put("id",m.getId());
				json.put("username",  m.getUserName());
				json.put("realname", m.getRealName());
				json.put("mobile", m.getMobile());
				json.put("state", m.getState());
				json.put("storeName", m.getStoreName());
				jsonlist.add(json);
			}
			jsonObject.put("state", "1");
			jsonObject.put("result", "ok");
			jsonObject.put("data", jsonlist);
			out.print(jsonObject.toString());
			out.close();
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 删除用户--非物理删除
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delmember", method = {RequestMethod.GET,RequestMethod.POST})  
	public String delmember(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		JSONArray paramList = JSONArray.parseArray(request.getParameter("param"));
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			for(int i=0;i<paramList.size();i++){
				long id = paramList.getJSONObject(i).getLongValue("id");	//会员ID
				memberService.delMember(id);				
			}
			jsonObject.put("state", "1");
			jsonObject.put("result", "删除成功");
			out.print(jsonObject.toString());
			out.close();
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 修改员工状态0在职 1离职
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatememberstate", method = {RequestMethod.GET,RequestMethod.POST})  
	public String updatememberstate(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		//long id = StrUtils.getLong(request, "id" ,0);
		//int state = StrUtils.getInt(request, "state", 0);
		JSONArray paramList = JSONArray.parseArray(request.getParameter("param"));
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			for(int i=0;i<paramList.size();i++){
				long id = paramList.getJSONObject(i).getLongValue("id");  //会员id
				int state = paramList.getJSONObject(i).getIntValue("state"); //状态
				memberService.updateMemberState(id, state);
			}
			jsonObject.put("state", "1");
			jsonObject.put("result", "修改成功");
			out.print(jsonObject.toString());
			out.close();
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
}
