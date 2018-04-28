package com.market.maicheng.controllers.webadmin;

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

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.controllers.web.BaseController;
import com.market.maicheng.model.Brand;
import com.market.maicheng.service.BrandService;
@Controller
@RequestMapping(value = "/webadmin/brand")
public class AdminBrandController extends BaseController{
	@Autowired
	private BrandService brandService;
	/**
	 * 添加品牌-页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.GET)  
    public ModelAndView adduser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		List<Brand> brandlist = brandService.getBrandList();
		modelMap.put("brandlist", brandlist);
        return new ModelAndView("/views/manage/brand/add", modelMap);
    }
	
	
	@RequestMapping(value = "/toadd",method = RequestMethod.POST)  
	@ResponseBody
    public int toadd(@RequestParam(value="brandName",required = false,defaultValue="")String brandName,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		System.out.println(request.getCharacterEncoding());
		long pid = StrUtils.getLong(request, "pid");
//		String brandName = StrUtils.getString(request, "brandName");
		String brandInitial = StrUtils.getString(request, "brandInitial","");
		if("".equals(brandName)){
			return 0;
		}
		if("".equals(brandInitial)){
			brandInitial = StrUtils.getPinyinA(brandName);
		}
		Brand brand = new Brand();
		brand.setBrandName(brandName);
		brand.setBrandInitial(brandInitial);
		brand.setIsDel(0);
		brand.setPid(pid);
		
		return brandService.addBrand(brand);
	}
	
	@RequestMapping(value = "/delbrand",method = RequestMethod.POST)  
	@ResponseBody
    public int delbrand(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long bid = StrUtils.getLong(request, "id");
		if(bid==0){
			return 0;
		}
		
		return brandService.delBrand(bid);
	}
	@RequestMapping(value = "/list",method =  {RequestMethod.GET,RequestMethod.POST})  
    public String list(@RequestParam(value="brandName",required = false,defaultValue="")String brandName,
    		HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		int pageNum = StrUtils.getInt(request, "p",1);
//		String brandName = StrUtils.getString(request, "brandName");
		RetInfo retInfo = brandService.getBrandListByPage(brandName,pageNum);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<Brand> pageVo = (PageVo<Brand>) retInfo.getObject();
			Map<String, String> args  = new HashMap<String, String>();
			args.put("pageVo", pageVo+"");
			args.put("brandName", brandName);
			pageVo.setArgs(args);
			pageVo.setCount(brandService.getBrandListByCount(brandName));
			modelMap.put("pageVo", pageVo);
			modelMap.put("brandName", brandName);
			modelMap.put("DateUtil", DateUtils.class);
		}
		return "/views/manage/brand/list";
	}
	
//	/**
//	 * 联动框
//	 * @param request
//	 * @param response
//	 * @param modelMap
//	 * @return
//	 */
//	@RequestMapping(value = "/getlistforpid",method = {RequestMethod.POST,RequestMethod.GET})  
//	@ResponseBody
//    public List<Brand> getlistforpid(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
//		long pid = StrUtils.getLong(request, "pid");
//		List<Brand> clist = brandService.getBrandListByPid(pid);
//		return clist;
//    }
}
