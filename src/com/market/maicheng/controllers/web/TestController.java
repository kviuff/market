package com.market.maicheng.controllers.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.market.maicheng.common.utils.PropertyUtils;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	@RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})  
	public String test(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		//System.out.println(this.getClass().getClassLoader().getResource("/").getPath());
		String path = request.getRealPath("/");
		System.out.println(path);
		return null;
	}
}
