package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.CookieUtils;
import com.market.maicheng.common.utils.ExceptionUtil;
import com.market.maicheng.common.utils.Md5;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.AdminUser;
import com.market.maicheng.model.Member;
import com.market.maicheng.service.AdminUserService;
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;


@Controller
@RequestMapping(value = "/webadmin")
public class AdminLoginController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)  
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		return new ModelAndView("/views/manage/login", modelMap);
	}
	
	/***
	 * 登录操作
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tologin", method = RequestMethod.POST)  
	public ModelAndView tologin(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try{
			//验证码验证
			String vcode = StrUtils.getString(request, "tCode");
			String strNum = CookieUtils.getCookieByName(request, "checkCode").getValue();
			
			//获取用户信息
			String username = StrUtils.getString(request, "tUserName");
			String password = StrUtils.getString(request, "tUserwd");
			
			AdminUser user = adminUserService.getAdminUserByUserPass(username, Md5.md5(password));
			out = response.getWriter();
			if(strNum == null || !strNum.equals(vcode)){
				out.print("验证码错误");
			}else if(user == null){
				out.print("用户密码错误");
			}else{
				user.setLogintime(new Date().getTime());
				if(adminUserService.updateAdminUser(user) == 1){
					CookieUtils.setcookie(response, Constants.LOGINED, String.valueOf(user.getAdminuserid()));
					out.print("1");
				}else{
					out.print("登陆失败");
				}				
			}
			out.close();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			modelMap.put("errorMessage",ExceptionUtil.getExceptionMessage(e));
			return new ModelAndView("/500", modelMap);
		}
	}
	
	/**
	 * 退出登陆
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/loginout",method = RequestMethod.GET)  
    public String loginout(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		//request.getSession().removeAttribute(Constants.LOGINED);
		Cookie cookie = new Cookie(Constants.LOGINED, null); 
		cookie.setMaxAge(0);
		response.addCookie(cookie); 
		try {
			response.sendRedirect("../webadmin/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
	
	
	@RequestMapping(value = "/mlogin", method = RequestMethod.GET)  
	public ModelAndView mlogin(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		return new ModelAndView("/views/manage/mlogin", modelMap);
	}
	
	/***
	 * 登录操作
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mtologin", method = RequestMethod.POST)  
	public ModelAndView mtologin(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try{
			
			//获取用户信息
			String username = StrUtils.getString(request, "tUserName");
			String password = StrUtils.getString(request, "tUserwd");
			
			Member member = memberService.getMemberForUserName(username);
			out = response.getWriter();
			
			if(member != null){
				if(member.getPassword().equals(Md5.md5(password))){
					CookieUtils.setcookie(response, Constants.LOGINED_MERCHANT, String.valueOf(member.getMerchantid()));
					member.setLoginTime(new Date().getTime());
					memberService.updateMember(member);
					out.print("1");
				}else{
					out.print("密码错误");
				}
				
			}else{
				out.print("用户密码错误");
			}
			
			out.close();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			modelMap.put("errorMessage",ExceptionUtil.getExceptionMessage(e));
			return new ModelAndView("/500", modelMap);
		}
	}
	
	/**
	 * 退出登陆
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/mloginout",method = RequestMethod.GET)  
    public String mloginout(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		//request.getSession().removeAttribute(Constants.LOGINED);
		Cookie cookie = new Cookie(Constants.LOGINED, null); 
		cookie.setMaxAge(0);
		response.addCookie(cookie); 
		try {
			response.sendRedirect("../webadmin/mlogin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

}
