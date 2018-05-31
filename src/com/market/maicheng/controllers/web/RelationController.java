package com.market.maicheng.controllers.web;

import java.util.Date;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Member;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.model.Relation;
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;
import com.market.maicheng.service.RelationService;

import wechat.WeChatPush;

@Controller
@RequestMapping(value = "/customer")
public class RelationController extends BaseController {
	@Autowired
	private RelationService relationService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MerchantService merchantService;

	/**
	 * 获取申请价格查看的客户列表
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getCustomerApplyList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject getCustomerApplyList(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		long merchantID = StrUtils.getLong(request, "mid");// 商家id
		int pagesize = StrUtils.getInt(request, "pagesize", 10); // 每页最大数量
		int pagenum = StrUtils.getInt(request, "pagenum", 1); // 当前页数
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		RetInfo retInfo = relationService.getCustomerList(merchantID, pagenum, pagesize);
		if (retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT) {
			PageVo<Relation> pageVo = (PageVo<Relation>) retInfo.getObject();
			pageVo.setCount(relationService.getCustomerListForCount(merchantID));
			for (Relation r : pageVo.getList()) {
				json = new JSONObject();
				json.put("id", r.getId());
				json.put("relaid", r.getRelaid());
				Member member = memberService.getMemberForid(r.getRelaid());
				if (member != null) {
					json.put("relaName", member.getUserName());
				}
				json.put("createid", r.getCreateid());
				json.put("pricLevel", r.getPricLevel());
				json.put("mid", r.getMid());
				json.put("createtime", r.getCreatetime());
				json.put("contacts", r.getContacts());
				json.put("address", r.getMobile());

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

	/**
	 * 获取用户对应商家价格等级
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getMerchantPricLevel", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject getMerchantPricLevel(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		long merchantID = StrUtils.getLong(request, "mid");// 商家id
		long relaid = StrUtils.getLong(request, "relaid");// 用户id
		JSONObject jsonObject = new JSONObject();
		int level = relationService.getMerchantPricLevel(relaid, merchantID);
		jsonObject.put("state", "1");
		jsonObject.put("data", level);
		jsonObject.put("result", "成功获取用户对应商家价格等级");
		return jsonObject;
	}

	/**
	 * 授权客户价格等级
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/setMerchantPricLevel", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject setMerchantPricLevel(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		long merchantID = StrUtils.getLong(request, "mid");// 商家id
		String uidsStr = StrUtils.getString(request, "uids");// 商家id
		int pricLevel = StrUtils.getInt(request, "pricLevel");// 价格等级 1：会员价格等级，2：代理商价格等级
		String[] uids = uidsStr.split(",");

		JSONObject jsonObject = new JSONObject();
		try {
			int state = 0;
			for (String uid : uids) {
				state += relationService.updatePricLevel(merchantID, Long.parseLong(uid), pricLevel);
			}
			if (state > 0) {
				for (String uid : uids) {
					// 给商户发送微信消息
					Merchant merchant = merchantService.getMerchantByUserid(Long.valueOf(uid));
					Map<String, Object> weixinmap = new HashMap<String, Object>();
					weixinmap.put("openId", "");
					weixinmap.put("first", "申请查看价格通知");
					weixinmap.put("name", merchant.getShopName());
					weixinmap.put("authorizetime", DateUtils.formatLongToStr(System.currentTimeMillis(), ""));
					weixinmap.put("authorizestatus", "授权成功");
					weixinmap.put("remark", "您向供货商" + merchant.getShopName() + "申请查看价格已通过商家审核！");
					WeChatPush.deliverTemplateSendToStoreForApplyPriceForResult(weixinmap);
				}
				
				jsonObject.put("state", "1");
				jsonObject.put("result", "设置成功");
			} else {
				jsonObject.put("state", "2");
				jsonObject.put("result", "设置失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 关系表 员工-客户 收藏
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addcust", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject addcust(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();

		long createid = StrUtils.getLong(request, "createid"); // 创建人--会员ID
		long mid = StrUtils.getLong(request, "mid"); // 商家id
		int pricLevel = StrUtils.getInt(request, "pricLevel", 1);// 商家添加，默认为会员等级
		int fromType = StrUtils.getInt(request, "fromType", 1); // 添加类型,默认商家添加
		long relaid = StrUtils.getLong(request, "relaid"); // 关注ID
		String address = StrUtils.getString(request, "address"); // 门店地址
		String contacts = StrUtils.getString(request, "contacts"); // 联系人

		try {
			long nowTime = new Date().getTime();
			boolean flag = false;
			if (relationService.isExistRelation(relaid, mid)) {
				jsonObject.put("state", 2);
				jsonObject.put("result", "添加失败,已存在");
				return jsonObject;

			}
			if (memberService.getMemberForid(relaid) == null) {
				jsonObject.put("state", 0);
				jsonObject.put("result", "客户ID不存在");
				flag = false;
			} else {
				flag = true;
			}
			if (relaid == createid) {
				fromType = 0;// 自己申请价格查看
			}
			if (flag) {
				Relation r = new Relation();
				r.setMid(mid);
				r.setId(IDGenerator.getID());
				r.setCreateid(createid);
				r.setRelaid(relaid);
				r.setCreatetime(nowTime);
				r.setIsdel(0);

				r.setFromType(fromType);
				r.setMobile(address);
				r.setPricLevel(pricLevel);
				r.setContacts(contacts);

				int state = relationService.insert(r);
				if (state == 0) {
					jsonObject.put("state", 2);
					jsonObject.put("result", "添加失败");
				} else {
					jsonObject.put("state", 1);
					jsonObject.put("result", "ok");
					// 给商户发送微信消息
					Member member = memberService.getMemberForid(createid);
					Map<String, Object> weixinmap = new HashMap<String, Object>();
					weixinmap.put("openId", "");
					weixinmap.put("first", "申请查看价格通知");
					weixinmap.put("name", member.getRealName());
					weixinmap.put("accountnumber", member.getUserName());
					weixinmap.put("applytime", DateUtils.formatLongToStr(nowTime, ""));
					weixinmap.put("remark", "您好，有用户提出申请查看价格，请及时处理审核授权！");
					WeChatPush.deliverTemplateSendToStoreForApplyPrice(weixinmap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * 获取关系列表 员工-客户 收藏
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/custlist", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject custlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();

		long createid = StrUtils.getLong(request, "createid", 0); // 创建人
		long mid = StrUtils.getLong(request, "mid", 0); // 商家id
		int pagesize = StrUtils.getInt(request, "pagesize", 10); // 每页最大数量
		int pagenum = StrUtils.getInt(request, "pagenum", 1); // 当前页数
		long classid = StrUtils.getLong(request, "classid", 0); // 分类id

		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		// 如果是商家主账号，则获取该商家全部客户,否则账号只能获取自己对应的客户
		Merchant m = merchantService.getMerchantByid(mid);
		if (m.getUserid() == createid) {
			createid = 0;
		}

		RetInfo retInfo = relationService.getRelationListByCreateid(mid, createid, classid, pagenum, pagesize);

		if (retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT) {
			PageVo<Relation> pageVo = (PageVo<Relation>) retInfo.getObject();
			pageVo.setCount(relationService.getRelationListByCreateidForCount(mid, createid, classid));
			for (Relation r : pageVo.getList()) {
				json = new JSONObject();
				json.put("id", r.getId());
				json.put("relaid", r.getRelaid());
				Member member = memberService.getMemberForid(r.getRelaid());
				if (member != null) {
					json.put("relaName", member.getUserName());
				}
				json.put("createid", r.getCreateid());
				json.put("pricLevel", r.getPricLevel());
				json.put("mid", r.getMid());
				json.put("createtime", r.getCreatetime());
				json.put("contacts", r.getContacts());
				json.put("address", r.getMobile());
				json.put("fromType", r.getFromType());

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

	@RequestMapping(value = "/delcust", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject delcust(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();

		JSONArray paramList = JSONArray.parseArray(request.getParameter("param"));

		for (int i = 0; i < paramList.size(); i++) {
			long id = paramList.getJSONObject(i).getLongValue("id"); // 客户id
			relationService.delete(id);
		}
		jsonObject.put("state", 1);
		jsonObject.put("result", "ok");

		return jsonObject;
	}
}
