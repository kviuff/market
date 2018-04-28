package com.market.maicheng.controllers.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Brand;
import com.market.maicheng.service.BrandService;

@Controller
@RequestMapping(value = "/brand")
public class BrandController extends BaseController{
	@Autowired
	private BrandService brandService;
	
	/**
	 * 添加品牌-页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/add",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
    public JSONObject addBrand(@RequestParam(value="brandName",required = false,defaultValue="")String brandName,
    		@RequestParam(value="brandInitial",required = false,defaultValue="")String brandInitial,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		long pid = StrUtils.getLong(request, "pid");
//		String brandName = StrUtils.getString(request, "brandName");
//		String brandInitial = StrUtils.getString(request, "brandInitial","");
		if("".equals(brandName)){
			jsonObject.put("state", 2);
			jsonObject.put("result", "参数不能为空");
			return jsonObject;
		}
		if("".equals(brandInitial)){
			brandInitial = StrUtils.getPinyinA(brandName);
		}
		Brand brand = new Brand();
		brand.setBrandName(brandName);
		brand.setBrandInitial(brandInitial);
		brand.setIsDel(0);
		brand.setPid(pid);
		
		jsonObject.put("state", brandService.addBrand(brand));
		jsonObject.put("result", "ok");
		jsonObject.put("data", brand.getId());
        return jsonObject;
    }

}
