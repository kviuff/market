package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.controllers.web.BaseController;
import com.market.maicheng.model.Member;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.model.Product;
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;

/**
 * 商户后台控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/webadmin/merchant")
public class AdminMerchantController extends BaseController{

	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})  
	public String list(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		int pageNum = StrUtils.getInt(request, "p",1);
		int auditState = StrUtils.getInt(request, "state",0); //审核状态 0未审核 1已审核 2拒绝
		String title = StrUtils.getString(request, "title"); //审核状态 0未审核 1已审核 2拒绝
		Member member;
		RetInfo retInfo = merchantService.getMerchantList(pageNum,auditState,title,-1,-1,-1,-1,Constants.PAGESIZE);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<Merchant> pageVo = (PageVo<Merchant>) retInfo.getObject();
			Map<String, String> args  = new HashMap<String, String>();
			args.put("state", auditState+"");
			args.put("title", title);
			args.put("pageVo", pageVo+"");
			pageVo.setArgs(args);
			pageVo.setCount(merchantService.getMerchantByCount(auditState,title,-1,-1,-1,-1));
			for(Merchant m : pageVo.getList()){
				//更新用户
				member = memberService.getMemberForid(m.getUserid());
				if(member!=null){
					m.setAccountName(member.getUserName());
				}
				
			}
			modelMap.put("pageVo", pageVo);
			modelMap.put("DateUtil", DateUtils.class);
			modelMap.put("state", auditState);
			modelMap.put("title", title);
		}
		return "/views/manage/merchant/list";
	}
	
	@RequestMapping(value = "/changestate", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject changestate(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		long id = StrUtils.getLong(request, "id");
		int state = StrUtils.getInt(request, "state");
		
		JSONObject jsonObject = new JSONObject();
			int res = merchantService.changeMerchantState(id, state);
			
			if(res==0){
				jsonObject.put("state", 0);
				return jsonObject;
			}
			Merchant merchant = merchantService.getMerchantByid(id);
			if(merchant==null){
				jsonObject.put("state", 0);
				return jsonObject;
			}
			long userid = merchant.getUserid();
			//更新用户
			Member member = memberService.getMemberForid(userid);
			if(member==null){
				jsonObject.put("state", 0);
				return jsonObject;
			}
			member.setMerchantid(merchant.getId()); //关联商家
//			if(state==1){//通过
				member.setUserType(1);//成为商家用户
//			}else if(state==2){//拒绝
//				if(member.getUserType()==5){
//					member.setUserType(6);//成为商家用户
//				}
//				
//			}
			
			res = memberService.updateMember(member);
			jsonObject.put("state", res);
		
		return jsonObject;
	}
	
	@RequestMapping(value = "/changeRecommend", method = {RequestMethod.GET,RequestMethod.POST})  
	public String changeRecommend(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		long id = StrUtils.getLong(request, "id");
		int recommend = StrUtils.getInt(request, "recommend");
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		
		try{
			out = response.getWriter();
			int res = merchantService.changeMerchantRecommend(id,new Date().getTime(), recommend);
			jsonObject.put("state", res);
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
	
	@RequestMapping(value = "/changePassword", method = {RequestMethod.GET,RequestMethod.POST})
	public String changePassword (HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
		long mechantId = StrUtils.getLong(request, "mechantId",1);
		modelMap.put("mechantId", mechantId);
		return "/views/manage/merchant/usermodify";
	}
}
