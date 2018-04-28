package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Advert;
import com.market.maicheng.model.AdvertType;
import com.market.maicheng.service.AdvertService;
import com.market.maicheng.service.AdvertTypeService;


@Controller
@RequestMapping(value = "/webadmin/advert")
public class AdminAdvertController {
	@Autowired
	private AdvertService advertService;
	
	@Autowired
	private AdvertTypeService advertTypeService;
	
	/**
	 * 广告列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)  
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long typeid = StrUtils.getLong(request, "typeid", 0);
		modelMap.put("aTypeList", advertTypeService.getAdvertType());
		modelMap.put("typeid", typeid);
		modelMap.put("adtype", advertTypeService.getAdvertTypeByID(typeid));
		if(typeid > 0){
			modelMap.put("adlist", advertService.getAdvertForType(typeid));
		}
		return new ModelAndView("/views/manage/advert/index", modelMap);
	}
	
	/**
	 * 添加广告
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addadvert",method = RequestMethod.POST)  
    public String addadvert(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)throws IOException {
		String title = StrUtils.getString(request, "title");
		String link = request.getParameter("link");
		String url = request.getParameter("url");
		long typeid = StrUtils.getLong(request, "typeid");
		
		Advert a = new Advert();
		a.setId(IDGenerator.getID());
		a.setTitle(title);
		a.setLink(link);
		a.setUrl(url);
		a.setType(typeid);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(advertService.addAdvert(a));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 删除广告
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "deladvert",method = RequestMethod.POST)  
    public String deladvert(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)throws IOException {
		long aid = StrUtils.getLong(request, "aid");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(advertService.delAdvertForID(aid));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 修改广告页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "upadvert",method = RequestMethod.GET)  
    public String upadvert(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)throws IOException {
		long id = StrUtils.getLong(request, "id");
		modelMap.put("advert", advertService.getAdvertForID(id));
		return "/views/manage/advert/upadvert";
	}
	
	/**
	 * 修改广告内容
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "toupadvert",method = RequestMethod.POST)  
    public String toupadvert(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)throws IOException {
		long id = StrUtils.getLong(request, "id");
		String title = StrUtils.getString(request, "title");
		String link = request.getParameter("link");
		String url = request.getParameter("url");
		
		Advert a = advertService.getAdvertForID(id);
		a.setTitle(title);
		a.setLink(link);
		a.setUrl(url);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(advertService.upAdvert(a));
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 添加广告类别
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addtype",method = RequestMethod.POST)  
    public String addtype(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)throws IOException {
		String ad_type_name = StrUtils.getString(request, "ad_type_name");
		AdvertType at = new AdvertType();
		at.setId(IDGenerator.getID());
		at.setAdTypeName(ad_type_name);
		at.setIsdel(0);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(advertTypeService.addAdvertType(at));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 广告类别管理
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "typemanage",method = RequestMethod.GET)  
    public ModelAndView typemanage(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("aTypeList", advertTypeService.getAdvertType());
		return new ModelAndView("/views/manage/advert/typemanage", modelMap);
	}
	
	/**
	 * 修改广告类别
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "updatetype",method = RequestMethod.POST)  
    public String updatetype(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)throws IOException {
		long typeid = StrUtils.getLong(request, "typeid");
		String typename = StrUtils.getString(request, "typename");
		AdvertType at = new AdvertType();
		at.setId(typeid);
		at.setAdTypeName(typename);
		at.setIsdel(0);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(advertTypeService.upAdvertType(at));
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value = "deltype",method = RequestMethod.POST)  
    public String deltype(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)throws IOException {
		long typeid = StrUtils.getLong(request, "typeid");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(advertTypeService.delAdvertType(typeid));
		out.flush();
		out.close();
		return null;
	}

}
