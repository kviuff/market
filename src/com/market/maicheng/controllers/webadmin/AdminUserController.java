package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.EnumConstant;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.Md5;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.AdminUser;
import com.market.maicheng.service.AdminUserService;

/**
 * 后台管理员后台控制
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/webadmin/manageuser")
public class AdminUserController {
	@Autowired
	private AdminUserService adminUserService;
	/**
	 * 添加管理员用户-页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/adduser",method = RequestMethod.GET)  
    public ModelAndView adduser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {

        return new ModelAndView("/views/manage/manageuser/adduser", modelMap);
    }
	
	/**
	 * 添加用户-aj
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toadduser",method = RequestMethod.POST)  
    public String toadduser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		response.setContentType("text/html;charset=UTF-8");
		long userid = Long.parseLong(CookieUtils.getCookieByName(request, Constants.LOGINED).getValue());
		PrintWriter out;
		try {
			out = response.getWriter();			
			
			String username = StrUtils.getString(request, "username");
			String password = StrUtils.getString(request, "password");
			String ju = StrUtils.getString(request, "ju");
			long cityid = StrUtils.getLong(request, "cityid");
			
			AdminUser adminuser = adminUserService.getAdminUserByUserName(username);
			if(adminuser != null){
				out.print("2");
				out.close();
				return null;
			}
						
			adminuser = new AdminUser();
			adminuser.setAdminuserid(IDGenerator.getID());
			adminuser.setAdminusername(username);
			adminuser.setAdminuserpass(Md5.md5(password));
			adminuser.setAdminuserrole(ju);
			adminuser.setCreatetime(new Date().getTime());
			adminuser.setCreateuser(userid);
			adminuser.setLogintime(0);
			adminuser.setCityid(cityid);
			if(cityid == 0){
				adminuser.setCityName("全国");
			}/*else{
				adminuser.setCityName(cityService.getCityByID(cityid).getCityName());
			}*/
			if(adminUserService.addAdminUser(adminuser) == 1){
				out.print("1");
			}else{
				out.print("0");
			}
			out.close();
		} catch (IOException e) {
			try {
				out = response.getWriter();
				out.print("0");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
		return null;
    }
	/**
	 * 修改管理员用户资料-页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/usermodify",method = RequestMethod.GET)  
    public ModelAndView usermodify(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long userid = StrUtils.getLong(request, "id", 0);
		if(userid == 0){
			userid = Long.parseLong(CookieUtils.getCookieByName(request, Constants.LOGINED).getValue());
		}
		AdminUser user = adminUserService.getAdminUserByID(userid);

		modelMap.put("user", user);
		//modelMap.put("cityList", cityService.getCityList());
        return new ModelAndView("/views/manage/manageuser/usermodify", modelMap);
    }
	
	/**
	 * 修改管理员用户资料-aj
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/tousermodify",method = RequestMethod.POST)  
    public String tousermodify(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try {
			long userid = StrUtils.getLong(request, "id", 0);
			String test = StrUtils.getString(request, "test","");
			if(userid == 0){
				userid = Long.parseLong(CookieUtils.getCookieByName(request, Constants.LOGINED).getValue());
			}
			AdminUser user = adminUserService.getAdminUserByID(userid);
			
			String password = StrUtils.getString(request, "password");
			long cityid = StrUtils.getLong(request, "cityid");
			if(cityid == 0){
				user.setCityName("全国");
			}/*else{
				user.setCityName(cityService.getCityByID(cityid).getCityName());
			}*/
			if(password.length() > 0){
				user.setAdminuserpass(Md5.md5(password));
			}
			user.setAdminuserrole(test);
			user.setCityid(cityid);
			out = response.getWriter();
			if(adminUserService.updateAdminUser(user) == 1){
				out.print("1");
			}else{
				out.print("0");
			}
			out.close();
		}catch (Exception e) {
			try {
				out = response.getWriter();
				out.print("0");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 管理员用户列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/userlist",method = RequestMethod.GET)  
    public ModelAndView userlist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		int pageNum = StrUtils.getInt(request, "p",1);
		String username = StrUtils.getString(request, "username" , null);
		
		RetInfo retInfo = adminUserService.getAdminUserListForPage(pageNum, username);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<AdminUser> pageVo = (PageVo<AdminUser>) retInfo.getObject();
			pageVo.setCount(adminUserService.getAdminUserListByCount(username));
			modelMap.put("pageVo", pageVo);
			modelMap.put("DateUtil", DateUtils.class);
			modelMap.put("EnumConstant", EnumConstant.class);
			modelMap.put("username", username);
		}
        return new ModelAndView("/views/manage/manageuser/userlist", modelMap);
    }
	
	/**
	 * 删除用户--非物理删除
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/deluser",method = RequestMethod.POST)  
    public String deluser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long userid = StrUtils.getLong(request, "id", 0);
		long loginuserid = Long.parseLong(CookieUtils.getCookieByName(request, Constants.LOGINED).getValue());
		PrintWriter out;
		
		try {
			out = response.getWriter();
			if(userid == loginuserid){
				out.print("3");		//不能删除自己
				out.close();
				return null;
			}
			if(adminUserService.deleteAdminUser(userid) == 1){
				out.print("0");		//成功
			}else{
				out.print("1");		//失败
			}
			out.close();
		} catch (IOException e) {
			try {
				out = response.getWriter();
				out.print("0");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
        return null;
    }
	
	@RequestMapping(value = "/unlockuser",method = RequestMethod.POST)  
	public String unlockuser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		long userid = StrUtils.getLong(request, "id", 0);
		AdminUser user = adminUserService.getAdminUserByID(userid);
		PrintWriter out;
		int lock = user.getUnlock();
		if(lock == 0){
			lock = 1;
		}else{
			lock = 0;
		}
		
		try {
			out = response.getWriter();
			
			if(adminUserService.unlockAdminUser(userid, lock) == 1){
				out.print("0");		//成功
			}else{
				out.print("1");		//失败
			}
			out.close();
		} catch (IOException e) {
			try {
				out = response.getWriter();
				out.print("0");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
		return null;
	}
}
