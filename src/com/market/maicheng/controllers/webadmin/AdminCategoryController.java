package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.controllers.web.BaseController;
import com.market.maicheng.model.Brand;
import com.market.maicheng.model.Category;
import com.market.maicheng.service.CategoryService;

/**
 * 商品类别后台控制
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "/webadmin/category")
public class AdminCategoryController extends BaseController{

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)  
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		
		List<Category> relist = new ArrayList<Category>();
		ditui(0L,relist);
		modelMap.put("attribute", relist);
        return new ModelAndView("/views/manage/category/list", modelMap);
    }
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)  
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		List<Category> relist = new ArrayList<Category>();
		ditui(0L,relist);
		modelMap.put("clist", relist);
        return new ModelAndView("/views/manage/category/add", modelMap);
    }
	
	@RequestMapping(value = "/update",method = RequestMethod.GET)  
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		List<Category> relist = new ArrayList<Category>();
		ditui(0L,relist);
		modelMap.put("clist", relist);
//		产品Id
		long id = StrUtils.getLong(request, "id", 0);
		Category catemodel = categoryService.getCategory(id);
		modelMap.put("cate", catemodel);
		String cateids = catemodel.getCateName();
        return new ModelAndView("/views/manage/category/update", modelMap);
    }
	/**
	 * 联动框
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getlistforpid",method = {RequestMethod.POST,RequestMethod.GET})  
    public ModelAndView getlistforpid(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long cateid = StrUtils.getLong(request, "cateid");
		List<Category> clist = categoryService.getProductCategoryList(cateid,-1,0);
		JSONArray jsonarray = new JSONArray();;
		JSONObject json = null;
		for(Category c : clist){
			json = new JSONObject();
			json.put("id", c.getId());
			json.put("cateName", c.getCateName());
			json.put("sid", c.getId()+"");
			jsonarray.add(json);
		}
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			out.print(jsonarray);
			out.close();
		}catch(Exception e){
			try {
				out = response.getWriter();
				out.print("error");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
    }
	/**
	 * 修改页联动框(返回名字集合)
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getlistforUpdate",method = {RequestMethod.POST,RequestMethod.GET})  
    public ModelAndView getlistforupdate(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		List<Category> list = new ArrayList<Category>();
		String cateid = StrUtils.getString(request, "cateid",null);
		if(cateid!=null){
			if(cateid.indexOf("-")>-1){
				for (int i = 0; i < cateid.split("-").length; i++) {
					Category catemodel  = new Category();
//					查询同级别分类
					catemodel = categoryService.getCategory(Long.parseLong(cateid.split("-")[i]));
					if(catemodel!=null){
						List<Category> clist = categoryService.getProductCategoryList(catemodel.getPid(),-1,0);
						catemodel.setCateList(clist);
						if(catemodel!=null){
							list.add(catemodel);
						}
					}
				}
			}else{
				Category catemodel = categoryService.getCategory(Long.parseLong(cateid));
				List<Category> clist = categoryService.getProductCategoryList(catemodel.getPid(),-1,0);
				if(catemodel!=null){
					catemodel.setCateList(clist);
					list.add(catemodel);
				}
			}
		}
		
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			//out.print(JSONArray.fromObject(list).toString());
			out.close();
		}catch(Exception e){
			try {
				out = response.getWriter();
				out.print("error");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/getalllistforpid",method = {RequestMethod.POST,RequestMethod.GET})  
    public ModelAndView getalllistforpid(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		System.out.println(111);
		long cateid = StrUtils.getLong(request, "cateid");
		List<Category> clist = new ArrayList<Category>();
		ditui(cateid,clist);
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try{
			out = response.getWriter();
			//out.print(JSONArray.fromObject(clist).toString());
			out.close();
		}catch(Exception e){
			try {
				out = response.getWriter();
				out.print("error");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
    }
	
	@RequestMapping(value = "/toadd",method = {RequestMethod.POST,RequestMethod.GET})  
    public ModelAndView toadd(@RequestParam(value="cateName",required = false,defaultValue="")String cateName,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws Exception{
		long id = StrUtils.getLong(request, "id", 0);
		long pid = StrUtils.getLong(request, "pid");
		String icon = request.getParameter("icon");
		String icon1 = request.getParameter("icon1");
		String icon2 = request.getParameter("icon2");
//		String cateName = StrUtils.getString(request, "cateName");
//		cateName = cateName.replace("\\", "");
		String states = StrUtils.getString(request, "states");
		int sort = StrUtils.getInt(request, "sort",0);
		Category c = new Category();
		if(id != 0){
			c = categoryService.getCategory(id);
			c.setCateName(cateName);
			c.setPid(pid);
			c.setIsdel(0);
			c.setIcon(icon);
			c.setIcon1(icon1);
			c.setIcon2(icon2);
			c.setStates(states);
			c.setSort(sort);
			if(pid == 0){
				c.setLevel(0);
			}else{
				c.setLevel(categoryService.getCategory(pid).getLevel()+1);				
			}
			categoryService.updateCate(c);
		}else{
			c.setId(IDGenerator.getID());
			c.setCateName(cateName);
			c.setPid(pid);
			c.setIsdel(0);
			c.setIcon(icon);
			c.setIcon1(icon1);
			c.setIcon2(icon2);
			c.setStates(states);
			c.setSort(sort);
			if(pid == 0){
				c.setLevel(0);
			}else{
				c.setLevel(categoryService.getCategory(pid).getLevel()+1);				
			}
			categoryService.addCate(c);
		}
		response.sendRedirect("list");
        return null;
    }
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)  
	@ResponseBody
    public int delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		long id = StrUtils.getLong(request, "id", 0);
		int result = 0;
		result = categoryService.delCate(id);
        return result;
    }
	
	@RequestMapping(value = "/upistop",method = RequestMethod.POST)  
    public String upistop(HttpServletRequest request,HttpServletResponse response) throws Exception{
		long id = StrUtils.getLong(request, "id", 0);
		int istop = StrUtils.getInt(request, "istop",0);
		if(istop == 0){
			istop = 1;
		}else{
			istop = 0;
		}
		PrintWriter out = response.getWriter();
		out.print(categoryService.updateIsTop(id, istop));
		out.close();
        return null;
    }
	
	private List<Category> ditui(long pid,List<Category> relist){
		List<Category> clist = categoryService.getProductCategoryList(pid,-1,0);
		for(Category c :clist){
			relist.add(c);
			ditui(c.getId(),relist);
		}
		return relist;
	}
}
