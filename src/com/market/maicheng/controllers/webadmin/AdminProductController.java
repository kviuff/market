package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.Md5;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Member;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.model.Product;
import com.market.maicheng.service.ProductService;

/**
 * 用户管理后台控制
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/webadmin/product")
public class AdminProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)  
    public ModelAndView memberlist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		int pageNum = StrUtils.getInt(request, "p",1);
		long merchantID = StrUtils.getLong(request, "mid", 0);
		long categoryID = StrUtils.getLong(request, "cid", -1);
		String barcodeid = StrUtils.getString(request, "barcodeid" , null);
		String title = StrUtils.getString(request, "title" , null);
		if("null".equals(title)){
			title = null;
		}
		//排序属性1、品牌 2、发布时间
		int type = StrUtils.getInt(request, "type");
		String orderby = "order by id asc";
		switch (type) {
		case 1:
			orderby = "order by brandid desc";
			break;
		case 2:
			orderby = "order by createTime desc";
			break;

		default:
			orderby = "order by id asc";
			break;
		}
		RetInfo retInfo = productService.getProductsForPage(pageNum, merchantID, categoryID, barcodeid, title, orderby, Constants.PAGESIZE);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<Product> pageVo = (PageVo<Product>) retInfo.getObject();
			Map<String, String> args  = new HashMap<String, String>();
//			args.put("mid", merchantID+"");
//			args.put("cid", categoryID+"");
//			args.put("barcodeid", barcodeid+"");
			args.put("title", title);
			args.put("pageVo", pageVo+"");
			pageVo.setArgs(args);
			pageVo.setCount(productService.getProductsForPageByCount(merchantID, categoryID, barcodeid, title, orderby));
			modelMap.put("pageVo", pageVo);
			modelMap.put("DateUtil", DateUtils.class);
			modelMap.put("title", title);
		}
        return new ModelAndView("/views/manage/product/list", modelMap);
    }
	
	/**
	 * 删除用户--非物理删除
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)  
    public String deluser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long pid = StrUtils.getLong(request, "id", 0);
		PrintWriter out;
		try {
			out = response.getWriter();
			
			if(productService.delProduct(pid) == 1){
				out.print("0");		//成功
			}else{
				out.print("1");		//失败
			}
			out.close();
		} catch (IOException e) {
			try {
				out = response.getWriter();
				out.print("0");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
        return null;
    }
	
	@RequestMapping(value = "/changeBrandName", method = {RequestMethod.GET,RequestMethod.POST})
	public String changeBrandName (HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
		long goodsId = StrUtils.getLong(request, "goodsId",1);
		modelMap.put("goodsId", goodsId);
		return "/views/manage/product/brandmodify";
	}
	
	@RequestMapping(value = "/updateGoodsBrand", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject updateGoodsBrand(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		//获取参数
		long goodsId = StrUtils.getLong(request, "goodsId");
		String brandName = StrUtils.getString(request, "brandName",null);
		
		JSONObject jsonObject = new JSONObject();
		Product product = productService.getProductByID(goodsId);
		if(product != null){
			product.setBrandname(brandName);
			if(productService.updateProduct(product) == 1){
				jsonObject.put("state", "1");
				jsonObject.put("result", "更新成功");
			}else{
				jsonObject.put("state", "3");
				jsonObject.put("result", "更新失败");
			}
		}else{
			jsonObject.put("state", "2");
			jsonObject.put("result", "商品不存在");
		}
		
		return jsonObject;
	}
}
