package com.market.maicheng.controllers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.News;
import com.market.maicheng.service.NewsService;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
	@Autowired
	private NewsService newsservice;
	
	
	/**
	 * 新闻添加/编辑
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
		
		long userid = StrUtils.getLong(request, "mid");		//商户ID
		response.setContentType("text/html;charset=UTF-8");
		String title = StrUtils.getString(request, "title");
		String content = StrUtils.getString(request, "content");
		
		try{
			out = response.getWriter();
			News news = new News();
			news.setId(IDGenerator.getID());
			news.setTitle(title);
			news.setContent(content);
			news.setAddtime(new Date().getTime());
			news.setAuthor(userid);
			news.setIsdel(0);
			news.setTypes(1);
			
			int state = newsservice.addNews(news);
			if(state == 1){
				jsonObject.put("state", "1");
				jsonObject.put("result", "添加成功");
				jsonObject.put("data", news.getId());
			}else{
				jsonObject.put("state", "2");
				jsonObject.put("result", "添加失败");
			}
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
	 * 获取公告列表 mid 商户ID 不传则获取后台发布公告
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/newlist",method = RequestMethod.POST)  
    public ModelAndView newlist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		long mid = StrUtils.getLong(request, "mid", 0);
		
		try{
			out = response.getWriter();
			List<News> newlist = null;
			if(mid == 0){
				newlist = newsservice.getNewsByTypes(0, mid);
			}else{
				newlist = newsservice.getNewsByTypes(1, mid);
			}
			JSONArray jsonarray = new JSONArray();
			JSONObject json = null;
			for(News n : newlist){
				json = new JSONObject();
				json.put("id", n.getId());
				json.put("title", n.getTitle());
				json.put("content", n.getContent());
				json.put("addtime", DateUtils.getLongToDateTime(n.getAddtime()));
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
