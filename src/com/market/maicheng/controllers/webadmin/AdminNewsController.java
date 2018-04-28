package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.EnumConstant;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.News;
import com.market.maicheng.service.CategoryService;
import com.market.maicheng.service.NewsService;
@Controller
@RequestMapping(value = "/webadmin/news")
public class AdminNewsController {
	
	@Autowired
	private NewsService newsservice;
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)  
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
        return new ModelAndView("/views/manage/news/add", modelMap);
    }
	
	/**
	 * 新闻添加/编辑
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toadd",method = RequestMethod.POST)  
    public ModelAndView toadd(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long userid = IDGenerator.getLoginID(request);
		response.setContentType("text/html;charset=UTF-8");
		String title = StrUtils.getString(request, "title");
		String zhaiyao = StrUtils.getString(request, "zhaiyao");
		String times = StrUtils.getString(request, "times");
		
		long id = StrUtils.getLong(request, "oid",0);
		News news = null;
		if(id == 0){
			news = new News();
			news.setId(IDGenerator.getID());
		}else{
			news = newsservice.getNewsById(id);
		}
		news.setTitle(title);
		news.setContent(zhaiyao);
		news.setAddtime(new Date().getTime());
		news.setAuthor(userid);
		news.setIsdel(0);
		news.setTypes(0);
		
		if(id != 0){
			newsservice.updateNews(news);
		}else{
			newsservice.addNews(news);
		}
        return null;
    }
	/**
	 * 编辑(查询)
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.GET)  
    public ModelAndView updateDisease(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		
		response.setContentType("text/html;charset=UTF-8");
		long id = 0;
		if(StrUtils.getString(request, "_id")!=null && !"".equals(StrUtils.getString(request, "_id"))){
			id = Long.parseLong(StrUtils.getString(request, "_id"));
		}
//		根据id差
		News dd = newsservice.getNewsById(id);
		modelMap.put("attribute", dd);
		return new ModelAndView("/views/manage/news/add", modelMap);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.GET)  
    public ModelAndView delDisease(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long oid = 0;
		if(request.getParameter("_id")!=null && !"".equals(request.getParameter("_id"))){
			oid = Long.parseLong(request.getParameter("_id"));
			newsservice.delNews(oid);
		}
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 新闻列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})  
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
//		pageNum多少页
		int pageNum = StrUtils.getInt(request, "p",1);
		String keyword = request.getParameter("username");
		int types = StrUtils.getInt(request, "types",-1);
//		pagesize每页多少条
		RetInfo retInfo = newsservice.getNewsListForPage("-1",types,keyword,pageNum,Constants.PAGESIZE);
		if(retInfo != null){
			PageVo<News> pageVo = (PageVo<News>) retInfo.getObject();
			pageVo.setCount(newsservice.getNewsListByCount("-1",types,keyword));
			modelMap.put("pageVo", pageVo);
			modelMap.put("DateUtils", DateUtils.class);
			modelMap.put("EnumConstant", EnumConstant.class);
			modelMap.put("username", keyword);
			modelMap.put("types", types);
			
		}
        return new ModelAndView("/views/manage/news/list", modelMap);
    }
	
}
