package com.market.maicheng.controllers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Advert;
import com.market.maicheng.model.Brand;
import com.market.maicheng.service.AdvertService;
import com.market.maicheng.service.BrandService;
import com.market.maicheng.service.CategoryService;

@Controller
@RequestMapping(value = "/")
public class OtherController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private AdvertService advertService;
	
//	/**
//	 * 获取商品分类
//	 * @param request
//	 * @param response
//	 * @param modelMap
//	 * @return
//	 */
//	@RequestMapping(value = "/getCategoryList",method =  {RequestMethod.GET,RequestMethod.POST})  
//    public String list(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
//		long pid = StrUtils.getLong(request, "pid", 0);
//		int num = StrUtils.getInt(request, "number", 0);
//		
//		JSONObject jsonObject = new JSONObject();
//		PrintWriter out = null;
//		response.setContentType("text/html;charset=UTF-8");
//		
//		try{
//			out = response.getWriter();
//			List<Category> relist = categoryService.getCategoryList(pid, -1,num);
//			JSONArray jsonarray = new JSONArray();
//			JSONObject json = null;
//			for(Category c : relist){
//				json = new JSONObject();
//				json.put("id", c.getId());
//				json.put("cateName", c.getCateName());
//				json.put("icon", c.getIcon());
//				json.put("pid", c.getPid());
//				jsonarray.add(json);
//			}
//			jsonObject.put("state", 1);
//			jsonObject.put("result", "ok");
//			jsonObject.put("data", jsonarray);
//			out.print(jsonObject.toString());
//			out.close();
//		}catch (Exception e) {
//			try {
//				out = response.getWriter();
//				jsonObject.put("state", 0);
//				jsonObject.put("result", "程序出错,请联系管理员");
//				out.print(jsonObject.toString());
//				out.close();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} 
//			e.printStackTrace();
//		}
//		return null;
//    }
	
	/**
	 * 获取商品品牌
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getBrandList",method = {RequestMethod.GET,RequestMethod.POST})  
    public String Brand(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long pid = StrUtils.getLong(request, "pid", 0);
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			out = response.getWriter();
			List<Brand> relist = brandService.getBrandList();
			JSONArray jsonarray = new JSONArray();
			JSONObject json = null;
			for(Brand c : relist){
				json = new JSONObject();
				json.put("id", c.getId());
				json.put("BrandName", c.getBrandName());
				jsonarray.add(json);
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", jsonarray);
			out.print(jsonObject.toString());
			out.close();
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", 0);
				jsonObject.put("result", "程序出错,请联系管理员");
				out.print(jsonObject.toString());
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
	 * 获取首页轮播广告图
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getAdvert",method = {RequestMethod.POST,RequestMethod.GET})  
    public ModelAndView getAdvert(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		
		try{
			out = response.getWriter();
			List<Advert> advertList = advertService.getAdvertForType(201709140384442675L);
			JSONArray jsonarray = new JSONArray();
			JSONObject json = null;
			for(Advert a : advertList){
				json = new JSONObject();
				json.put("id", a.getId());
				json.put("title", a.getTitle());
				json.put("link", a.getLink());
				json.put("imageurl", a.getUrl());
				jsonarray.add(json);
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", jsonarray);
			out.print(jsonObject.toString());
			out.close();
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", 0);
				jsonObject.put("result", "程序出错,请联系管理员");
				out.print(jsonObject.toString());
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
