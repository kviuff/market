package com.market.maicheng.controllers.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.service.NewsService;


@Controller
public class IndexController {
	@Autowired
	private NewsService newsservice;
	
	/**
	 * 获取首页广告轮播列表和公告列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addnews",method = RequestMethod.POST)  
    public ModelAndView toadd(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		return null;
	}
	
	@RequestMapping(value = "/",method = RequestMethod.GET)  
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		return new ModelAndView("/views/manage/login", modelMap);
	}
	
}
