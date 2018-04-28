package com.market.maicheng.controllers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Address;
import com.market.maicheng.service.AddressService;


@Controller
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	
	/**
	 * 地址列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getaddresslist",method = {RequestMethod.POST,RequestMethod.GET})  
	public String getaddresslist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		long userid = StrUtils.getLong(request, "userid");
		int pageNum = StrUtils.getInt(request, "pageNum",1);
		int pageSize = StrUtils.getInt(request, "pagesize",5);
		try{
			out = response.getWriter();
			if (userid != 0) {
				PageVo<Address> pageVo = null;
				RetInfo retInfo = addressService.getAddressListPage(userid, pageNum, pageSize);
				if(retInfo != null){
					pageVo = (PageVo<Address>) retInfo.getObject();
					pageVo.setCount(addressService.getAddressListCount(userid));
				}
				
				JSONArray json = new JSONArray();
				json.addAll(pageVo.getList());
				json.listIterator();
				jsonObject.put("state", "1");
				jsonObject.put("result", "ok");
				jsonObject.put("data", json);
				jsonObject.put("pageCount", pageVo.getPageCount());
				jsonObject.put("pageNum", pageNum);
			}else{
				jsonObject.put("state", "-1");
				jsonObject.put("result", "用户未登陆");
			}
			out.print(jsonObject.toString());
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "2");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
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
	 * 添加地址
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addaddress",method = {RequestMethod.POST,RequestMethod.GET})  
	public String addaddress(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		String receiver = StrUtils.getString(request, "receiver");//收货人
		int sex = StrUtils.getInt(request, "sex");//性别 0男1女
		String mobile = StrUtils.getString(request, "mobile");//联系电话
		String provinceName = StrUtils.getString(request, "provinceName");//省名称
		long provinceId = StrUtils.getLong(request, "provinceId");//省Id
		String cityName = StrUtils.getString(request, "cityName");//市名称
		long cityId = StrUtils.getLong(request, "cityId");//市ID
		String region = StrUtils.getString(request, "region");//区名称
		long regionid = StrUtils.getLong(request, "regionid");//区ID
		String address = StrUtils.getString(request, "address");//收货地址
		String zipcode = StrUtils.getString(request, "zipcode");//邮编
		int isdefault = StrUtils.getInt(request, "isdefault",0);
		long userid = StrUtils.getLong(request,"userid", 0);
			try{
				out = response.getWriter();
				if (userid != 0) {
					//将该会员下的所有地址都设置为非默认
					if(isdefault == 1){
						addressService.upAddressdefault(userid, 0);
					}
					Address ad = new Address();
					ad.setId(IDGenerator.getID());
					ad.setReceiver(receiver);
					ad.setSex(sex);
					ad.setMobile(mobile);
					ad.setProvinceName(provinceName);
					ad.setProvinceId(provinceId);
					ad.setCityName(cityName);
					ad.setCityId(cityId);
					ad.setRegion(region);
					ad.setRegionid(regionid);
					ad.setZipcode(zipcode);
					ad.setAddress(address);
					ad.setIsdefault(isdefault);
					ad.setUserid(userid);
					ad.setAddtime(new Date().getTime());
					out = response.getWriter();
					int res = addressService.addAddress(ad);
					if(res==1){
						jsonObject.put("state", "1");
						jsonObject.put("result", "添加成功");
					}
				}else{
					jsonObject.put("state", "-1");
					jsonObject.put("result", "用户未登陆");
				}
				out.print(jsonObject.toString());
			}catch (Exception e) {
				try {
					out = response.getWriter();
					jsonObject.put("state", "2");
					jsonObject.put("result", "程序出错，请联系管理员");
					out.print(jsonObject.toString());
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
	 * 修改地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateAddress",method = {RequestMethod.POST,RequestMethod.GET})  
	public String updateAddress(HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		String id = StrUtils.getString(request, "id","");
		String receiver = StrUtils.getString(request, "receiver","");//收货人
		int sex = StrUtils.getInt(request, "sex",-1);//性别 0男1女
		String mobile = StrUtils.getString(request, "mobile","");//联系电话
		String provinceName = StrUtils.getString(request, "provinceName","");//省名称
		long provinceId = StrUtils.getLong(request, "provinceId",0);//省Id
		String cityName = StrUtils.getString(request, "cityName","");//市名称
		long cityId = StrUtils.getLong(request, "cityId",0);//市ID
		String region = StrUtils.getString(request, "region","");//区名称
		long regionid = StrUtils.getLong(request, "regionid",0);//区ID
		String address = StrUtils.getString(request, "address","");//收货地址
		String zipcode = StrUtils.getString(request, "zipcode","");//邮编
		int isdefault = StrUtils.getInt(request, "isdefault",0);
		long userid = StrUtils.getLong(request,"userid", 0);
		
		try{
			out = response.getWriter();
			
			if (userid != 0) {
				if(id.length()>0){
					//将该会员下的所有地址都设置为非默认
					if(isdefault == 1){
						addressService.upAddressdefault(userid, 0);
					}
					Address address1 = addressService.getAddress(Long.parseLong(id));
					if(address1!=null){
						if(receiver.length()>0){
							address1.setReceiver(receiver);
						}
						if(sex!=-1){
							address1.setSex(sex);
						}
						if(mobile.length()>0){
							address1.setMobile(mobile);
						}
						if(provinceName.length()>0){
							address1.setProvinceName(provinceName);
						}
						if(provinceId>0){
							address1.setProvinceId(provinceId);
						}
						if(cityName.length()>0){
							address1.setCityName(cityName);
						}
						if(cityId>0){
							address1.setCityId(cityId);
						}
						if(region.length()>0){
							address1.setRegion(region);
						}
						if(regionid>0){
							address1.setRegionid(regionid);
						}
						if(zipcode.length()>0){
							address1.setZipcode(zipcode);
						}
						if(address.length()>0){
							address1.setAddress(address);
						}
						address1.setIsdefault(isdefault);
						int res = addressService.upAddress(address1);
						if(res == 1){
							jsonObject.put("state", "1");
							jsonObject.put("result", "修改成功");
						}else{
							jsonObject.put("state", "2");
							jsonObject.put("result", "修改失败");
						}
					}
				}
			}else{
				jsonObject.put("state", "-1");
				jsonObject.put("result", "用户未登陆");
			}

			out.print(jsonObject.toString());
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
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
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delAddress",method = {RequestMethod.POST,RequestMethod.GET})  
	public String delAddress(HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		long id = StrUtils.getLong(request, "id",0);
		try{
			out = response.getWriter();
			if(id>0){
					int res = addressService.delAddress(id);
					if(res == 1){
						jsonObject.put("state", "1");
						jsonObject.put("result", "删除成功");
					}else{
						jsonObject.put("state", "2");
						jsonObject.put("result", "删除失败");
					}
				}
			out.print(jsonObject.toString());
		}catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "0");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
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
}
