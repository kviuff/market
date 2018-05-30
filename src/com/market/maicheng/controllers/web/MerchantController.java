package com.market.maicheng.controllers.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Category;
import com.market.maicheng.model.Member;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.model.MerchantCategory;
import com.market.maicheng.model.Relation;
import com.market.maicheng.service.CategoryService;
import com.market.maicheng.service.FavoriteService;
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;
import com.market.maicheng.service.RelationService;

import wechat.WeChatPush;

@Controller
@RequestMapping(value = "/merchant")
public class MerchantController extends BaseController {

	@Autowired
	private MerchantService merchantService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FavoriteService favoriteService;

	/**
	 * 获取商家分类
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getCategoryList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		long pid = StrUtils.getLong(request, "pid", 0);
		int num = StrUtils.getInt(request, "number", 0);

		JSONObject jsonObject = new JSONObject();
		List<Category> relist = categoryService.getMerchantCategoryList(pid, -1, num);
		JSONArray jsonarray = new JSONArray();
		JSONObject json = null;
		for (Category c : relist) {
			json = new JSONObject();
			json.put("id", c.getId());
			json.put("cateName", c.getCateName());
			json.put("icon", c.getIcon());
			json.put("pid", c.getPid());
			jsonarray.add(json);
		}
		jsonObject.put("state", 1);
		jsonObject.put("result", "ok");
		jsonObject.put("data", jsonarray);
		return jsonObject;
	}

	/**
	 * 根据用户ID获取申请商户信息
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getmerchant", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject getmerchant(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 当前登录用户ID
		long userid = StrUtils.getLong(request, "userid");

		JSONObject jsonObject = new JSONObject();
		Merchant m = merchantService.getMerchantByUserid(userid);
		if (m != null) {
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			List catagoryIds = getCategoyy(m.getId());
			m.setMerchantClass(catagoryIds);
			jsonObject.put("data", m);
			// 获取商家分类列表

		} else {
			jsonObject.put("state", 2);
			jsonObject.put("result", "商家不存在");
		}
		return jsonObject;
	}

	private List<MerchantCategory> getCategoyy(long mid) {
		List<MerchantCategory> categorys = categoryService.getMerchantCategoryById(mid);
		// List<Long> categoryIds = new ArrayList<Long>();
		// for(MerchantCategory category:categorys){
		// categoryIds.add(category.getCid());
		// }
		// return categoryIds;
		return categorys;
	}

	/**
	 * 根据商户ID获取申请商户信息
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getmerchantbyid", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject getmerchantbyid(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 商户ID
		long merchantid = StrUtils.getLong(request, "merchantid");
		System.out.println("merchantid=======" + merchantid);
		long userid = StrUtils.getLong(request, "userid");

		JSONObject jsonObject = new JSONObject();
		Merchant m = merchantService.getMerchantByid(merchantid);
		if (m != null) {
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			List catagoryIds = getCategoyy(m.getId());
			m.setMerchantClass(catagoryIds);
			jsonObject.put("data", m);
			List<Relation> rlist = relationService.getRelationListByRelaid(merchantid, userid);
			if (rlist.size() > 0) {
				// jsonObject.put("isCollection", rlist.size());
				jsonObject.put("relationid", rlist.get(0).getId());
				jsonObject.put("pricLevel", rlist.get(0).getPricLevel());
			} else {
				// jsonObject.put("isCollection", 0);
				jsonObject.put("pricLevel", 0);
			}
			if (favoriteService.isExist(userid, merchantid)) {
				jsonObject.put("isCollection", 1);
			} else {
				jsonObject.put("isCollection", 0);
			}

		} else {
			jsonObject.put("state", 2);
			jsonObject.put("result", "商家不存在");
		}
		return jsonObject;
	}

	/**
	 * 申请商家入驻
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/apply", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject apply(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		
		try {

			Long nowTime = System.currentTimeMillis();
			// 当前登录用户ID
			long userid = StrUtils.getLong(request, "userid");

			// 申请商家字段
			String shopName = StrUtils.getString(request, "shopName");
			long provinceId = StrUtils.getLong(request, "provinceId");
			String provinceName = StrUtils.getString(request, "provinceName");
			long cityId = StrUtils.getLong(request, "cityId");
			String cityName = StrUtils.getString(request, "cityName");
			long regionid = StrUtils.getLong(request, "regionid");
			String region = StrUtils.getString(request, "region");
			String address = StrUtils.getString(request, "address"); // 详细地址
			String contacts = StrUtils.getString(request, "contacts"); // 联系人
			String phone = StrUtils.getString(request, "phone"); // 联系电话
			String email = StrUtils.getString(request, "email"); // 电子邮箱
			String businessLicense = request.getParameter("businessLicense"); // 营业执照
			String userName = StrUtils.getString(request, "userName");
			String carid = StrUtils.getString(request, "carid"); // 身份证号
			String caridPhoto = request.getParameter("caridPhoto"); // 手持身份证照片
			int merchantType = StrUtils.getInt(request, "merchantType");

			Merchant m = merchantService.getMerchantByUserid(userid);
			if (m != null) {
				jsonObject.put("state", "2");
				jsonObject.put("result", "该用户已经申请过，不能再申请");
				return jsonObject;
			}

			m = new Merchant();
			m.setId(IDGenerator.getID());
			m.setUserid(userid);
			m.setShopName(shopName);
			m.setProvinceId(provinceId);
			m.setProvinceName(provinceName);
			m.setCityId(cityId);
			m.setCityName(cityName);
			m.setRegionid(regionid);
			m.setRegion(region);
			m.setAddress(address);
			m.setContacts(contacts);
			m.setPhone(phone);
			m.setEmail(email);
			m.setBusinessLicense(businessLicense);
			m.setUserName(userName);
			m.setCarid(carid);
			m.setCaridPhoto(caridPhoto);
			m.setCreateTime(nowTime);
			m.setAuditState(0);
			m.setAuditTime(0);
			m.setAuditUserid(0);
			m.setMerchantType(merchantType);

			Member member = memberService.getMemberForid(userid);
			if (member == null) {
				jsonObject.put("state", "3");
				jsonObject.put("result", "用户不存在");
			} else {
				int state = merchantService.addMerchant(m);
				if (state == 1) {
					jsonObject.put("state", "1");
					jsonObject.put("result", "申请成功");
					jsonObject.put("data", userid);
					// 申请成功修改会员类型
					memberService.updateMemberUserType(userid, 1);
					
					// 给管理员发送微信消息
					Map<String, Object> weixinmap = new HashMap<String, Object>();
					weixinmap.put("openId", "");
					weixinmap.put("first", "入驻申请通知");
					weixinmap.put("applyname", userName);
					weixinmap.put("applytime", nowTime);
					weixinmap.put("remark", "有新商家申请入驻平台，请您及时审核！");
					WeChatPush.deliverTemplateSendToForApplyStore(weixinmap);
					
				} else {
					jsonObject.put("state", "2");
					jsonObject.put("result", "申请失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonObject.put("state", "2");
				jsonObject.put("result", "程序出错，请联系管理员");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return jsonObject;
	}

	/**
	 * 获取首页推荐商户
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getRecommendMerchant", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject getRecommendMerchant(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		int limit = StrUtils.getInt(request, "limit", 6);
		JSONObject jsonObject = new JSONObject();
		List<Merchant> merchantList = merchantService.getMerchantRecommendList(limit);
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (Merchant m : merchantList) {
			json = new JSONObject();
			json.put("id", m.getId());
			json.put("shopName", m.getShopName());
			json.put("merchantHead", m.getMerchantHead());
			jsonArray.add(json);
		}
		jsonObject.put("state", 1);
		jsonObject.put("result", "ok");
		jsonObject.put("data", jsonArray);
		return jsonObject;
	}

	/**
	 * 设置用户对应商家价格等级
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/setMerchantPubPric", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject updateMerchantPubPric(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		long merchantID = StrUtils.getLong(request, "mid");// 商家id
		int isPubPric = StrUtils.getInt(request, "isPubPric");// 是否公开价格
		JSONObject jsonObject = new JSONObject();
		int state = merchantService.updateMerchantPubPric(merchantID, isPubPric);
		if (state > 0) {
			jsonObject.put("state", "1");
			jsonObject.put("result", "成功设置用户对应商家价格等级");
		} else {
			jsonObject.put("state", "2");
			jsonObject.put("result", "设置用户对应商家价格等级失败");
		}

		return jsonObject;
	}

	/**
	 * 修改商铺信息
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/updateMerchant", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject updateMerchant(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		long merchantID = StrUtils.getLong(request, "merchantID");
		String merchantClassStrs = StrUtils.getString(request, "merchantClass"); // 商家分类
		String[] categoryArr = merchantClassStrs.split(",");

		String merchantLogo = request.getParameter("merchantLogo"); // 店铺logo
		String merchantHead = request.getParameter("merchantHead"); // 店铺头像
		String merchantAddress = StrUtils.getString(request, "merchantAddress"); // 店铺地址
		String merchantDes = StrUtils.getString(request, "merchantDes"); // 店铺描述
		String printRemarks = StrUtils.getString(request, "printRemarks"); // 打印备注

		JSONObject jsonObject = new JSONObject();
		List<Long> categoryIds = new ArrayList<Long>();
		if (categoryArr != null && categoryArr.length > 0) {
			for (String categoryStr : categoryArr) {
				categoryIds.add(Long.parseLong(categoryStr));
			}
		}
		Merchant m = merchantService.getMerchantByid(merchantID);
		if (m == null) {
			jsonObject.put("state", "3");
			jsonObject.put("result", "id不合法");
		} else {

			m.setMerchantLogo(merchantLogo);
			m.setMerchantHead(merchantHead);
			m.setMerchantAddress(merchantAddress);
			m.setMerchantDes(merchantDes);
			m.setPrintRemarks(printRemarks);
			int state = merchantService.updateMerchantInformation(m);
			// m.setMerchantClass(merchantClass);
			if (state == 1) {// 保存或更新商家分类
				state = merchantService.updateMerchantCategory(m.getId(), categoryIds);
			}

			if (state > 0) {
				jsonObject.put("state", "1");
				jsonObject.put("result", "提交成功");
			} else {
				jsonObject.put("state", "2");
				jsonObject.put("result", "提交失败");
			}
		}

		return jsonObject;
	}

	/**
	 * 获取商户列表
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getMerchantList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject getMerchantList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		int pageNum = StrUtils.getInt(request, "p", 1);
		int pagesize = StrUtils.getInt(request, "pagesize", Constants.PAGESIZE);
		long categoryID = StrUtils.getLong(request, "categoryID", -1);
		long provinceId = StrUtils.getLong(request, "provinceId", -1);
		long cityId = StrUtils.getLong(request, "cityId", -1);
		long regionid = StrUtils.getLong(request, "regionid", -1);
		String shopName = StrUtils.getString(request, "shopName", null);

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json;

		RetInfo retInfo = merchantService.getMerchantList(pageNum, 1, shopName, categoryID, provinceId, cityId,
				regionid, pagesize);
		if (retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT) {
			PageVo<Merchant> pageVo = (PageVo<Merchant>) retInfo.getObject();
			pageVo.setCount(merchantService.getMerchantByCount(1, shopName, categoryID, provinceId, cityId, regionid));

			for (Merchant m : pageVo.getList()) {
				json = new JSONObject();
				json.put("id", m.getId());
				json.put("shopName", m.getShopName());
				json.put("publicPric", m.getPublicPric());
				json.put("merchantHead", m.getMerchantHead());
				jsonArray.add(json);
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", jsonArray);
			jsonObject.put("pageNum", pageVo.getPageNum());
			jsonObject.put("pageCount", pageVo.getPageCount());
		} else {
			jsonObject.put("state", 2);
			jsonObject.put("result", "无数据");

		}
		return jsonObject;
	}
}
