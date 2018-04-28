package com.market.maicheng.controllers.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Category;
import com.market.maicheng.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController{
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 获取商品分类（从pid=0一级开始）
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getProductCategoryList",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
    public JSONObject list(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long pid = StrUtils.getLong(request, "pid", 0);
		int num = StrUtils.getInt(request, "number", 0);
		
		JSONObject jsonObject = new JSONObject();
			List<Category> relist = categoryService.getProductCategoryList(pid, -1,num);
			JSONArray jsonarray = new JSONArray();
			JSONObject json = null;
			for(Category c : relist){
				json = new JSONObject();
				json.put("id", c.getId());
				json.put("cateName", c.getCateName());
				json.put("icon", c.getIcon());
				json.put("pid", c.getPid());
				jsonarray.add(json);
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", jsonarray);
		return jsonObject;
    }
	
	/**
	 * 获取商品二级或二级以上分类
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getProductCategoryListLimit",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
    public JSONObject getCategoryListByMid(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long pid = StrUtils.getLong(request, "pid", 0);
		long mid = StrUtils.getLong(request, "mid", 0);
		int num = StrUtils.getInt(request, "number", 0);
		
		JSONObject jsonObject = new JSONObject();
			List<Category> relist = null;
			if(pid==0){
				relist = categoryService.getProductSecCategoryListLimit(mid, -1,num);
			}else{
				relist = categoryService.getProductCategoryListLimit(mid,pid, -1,num);
			}
			JSONArray jsonarray = new JSONArray();
			JSONObject json = null;
			for(Category c : relist){
				json = new JSONObject();
				json.put("id", c.getId());
				json.put("cateName", c.getCateName());
				json.put("icon", c.getIcon());
				json.put("pid", c.getPid());
				jsonarray.add(json);
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", jsonarray);
			return jsonObject;
    }
}
