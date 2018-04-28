package com.market.maicheng.controllers.webadmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.model.AdminUser;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.service.AdminUserService;
import com.market.maicheng.service.MerchantService;

@Controller
@RequestMapping("/webadmin") 
public class AdminMainController {
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private MerchantService merchantService;
	
	@RequestMapping(value = "/main",method = RequestMethod.GET)  
    public ModelAndView usermodify(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long userid = IDGenerator.getLoginID(request);
		System.out.println(userid);
		AdminUser user = adminUserService.getAdminUserByID(userid);
		modelMap.put("username", user.getAdminusername());
		modelMap.put("cityid", user.getCityid());
        return new ModelAndView("/views/manage/main", modelMap);
    }
	
	@RequestMapping(value = "/merchant",method = RequestMethod.GET)  
    public ModelAndView merchant(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long userid = IDGenerator.getMerchantLoginID(request);
		System.out.println(userid);
		Merchant merchant = merchantService.getMerchantByid(userid);
		modelMap.put("username", merchant.getShopName());
		modelMap.put("merchantid", merchant.getId());
        return new ModelAndView("/views/manage/main_merchant", modelMap);
    }
}
