package com.market.maicheng.controllers.web;

import java.io.IOException;
import java.util.Date;

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
import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.FileUtils;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Favorite;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.service.FavoriteService;
import com.market.maicheng.service.MerchantService;
/**
 * 功能:用户收藏店铺
 * 创建时间: 2017/12/20.17:27
 *
 * @author 高红飞(gaohongfeipc)
 * @version(版本)：TODO
 * @since JDK 7
 */
@Controller
@RequestMapping(value = "/favorite")
public class FavoriteController extends BaseController{
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private MerchantService merchantService;
	
	
	/**
	 * 关系表 员工-客户 收藏
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/add",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject addcust(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		
		long createid = StrUtils.getLong(request, "createid");		//创建人
		long mid = StrUtils.getLong(request, "mid");		//商家id

			boolean flag = false;
//			long classid = 0;
			if(favoriteService.isExist(createid, mid)){
				jsonObject.put("state", 2);
				jsonObject.put("result", "添加失败,已存在");
				return jsonObject;
				
			}

			Merchant merchant = merchantService.getMerchantByid(mid);
			if(merchant == null){
				jsonObject.put("state", 0);
				jsonObject.put("result", "商铺ID不存在");
				flag = false;
			}else{
				flag = true;
			}
			if(flag){
				Favorite f = new Favorite();
				f.setMid(mid);
				f.setCreateid(createid);
				f.setCreatetime(new Date().getTime());
				f.setIsdel(0);
				int state = favoriteService.insert(f);
				if(state == 0){
					jsonObject.put("state", 2);
					jsonObject.put("result", "添加失败");
				}else{
					jsonObject.put("state", 1);
					jsonObject.put("result", "ok");
				}
			}
			
			
		return jsonObject;
	}
	
	
	/**
	 * 获取收藏列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject custlist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		
		long createid = StrUtils.getLong(request, "createid", 0);		//创建人
		int pagesize = StrUtils.getInt(request, "pagesize", 10);		//每页最大数量
		int pagenum = StrUtils.getInt(request, "pagenum",1);			//当前页数
		long classid = StrUtils.getLong(request, "classid", 0);			//分类id
		
			JSONArray jsonArray = new JSONArray();
			JSONObject json;
			
			RetInfo retInfo = favoriteService.getList(createid,classid, pagenum, pagesize);
			
			if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
				PageVo<Favorite> pageVo = (PageVo<Favorite>) retInfo.getObject();
				pageVo.setCount(favoriteService.getListForCount(createid,classid));
				for(Favorite f : pageVo.getList()){
					json = new JSONObject();
					json.put("id",f.getId());
					json.put("relaid", f.getMid());
					json.put("createid", f.getCreateid());
					json.put("createtime", f.getCreatetime());
					Merchant m = merchantService.getMerchantByid(f.getMid());
					json.put("shopName", m.getShopName());
					json.put("merchantLogo", m.getMerchantLogo());
					json.put("merchantHead", m.getMerchantHead());
					json.put("merchantAddress", m.getMerchantAddress());
					jsonArray.add(json);
				}
				jsonObject.put("state", 1);
				jsonObject.put("result", "ok");
				jsonObject.put("data", jsonArray);
				jsonObject.put("pageNum", pageVo.getPageNum());
				jsonObject.put("pageCount", pageVo.getPageCount());
			}else{
				jsonObject.put("state", 2);
				jsonObject.put("result", "无数据");
			}
		return jsonObject;
	}
	
	@RequestMapping(value = "/del",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject delcust(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		
		JSONArray paramList = JSONArray.parseArray(request.getParameter("param"));

			for(int i=0;i<paramList.size();i++){
				long id = paramList.getJSONObject(i).getLongValue("id");	//收藏主键id
				favoriteService.delete(id);
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");

		return jsonObject;
	}
	
	@RequestMapping(value = "/getLastVersion",method = {RequestMethod.POST, RequestMethod.GET})  
	@ResponseBody
	public JSONObject getVersion(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObject = new JSONObject();
		FileUtils fu = new FileUtils();
		String result = fu.readAbsolutelyFile("E:\\tomcat7\\apache-tomcat-7.0.84\\webapps\\version.txt");
		//String result = fu.readAbsolutelyFile("/Users/kanglan/Documents/workspace/Eclipse/market/market/version.txt");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		jsonObject.put("state", 1);
		jsonObject.put("result", "ok");
		jsonObject.put("data", jsonArray);
		return jsonObject;
	}

}
