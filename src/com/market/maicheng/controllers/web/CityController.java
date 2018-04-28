package com.market.maicheng.controllers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.City;
import com.market.maicheng.service.CityService;


@Controller
public class CityController {

	@Autowired
	private CityService cityservice;
	/**
	 * 查询城市
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/searchCity",method = {RequestMethod.POST,RequestMethod.GET})  
	public String searchCity(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		long Pid = StrUtils.getLong(request, "pid",0);
		List<City> list = cityservice.getCityByPid(Pid);
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			JSONArray json = new JSONArray();
			json.addAll(list);
			json.listIterator();
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", json);
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
	 * 读数据的接口
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
//	@RequestMapping(value = "/citytoDB",method = {RequestMethod.POST,RequestMethod.GET})  
//	public String citytoDB(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
//		
//		List<City> la = new ArrayList<City>();
//		CitysUtils.ditui(0,la);
//		for (City city : la) {
//			cityservice.addCity(city);
//		}
//		return null;
//	}
}
