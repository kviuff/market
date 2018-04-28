package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;

/**
 * 用户管理后台控制
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/webadmin/member")
public class AdminMemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MerchantService merchantService;
	
	@RequestMapping(value = "/memberlist",method = RequestMethod.GET)  
    public ModelAndView memberlist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		int pageNum = StrUtils.getInt(request, "p",1);
		String username = StrUtils.getString(request, "username" , null);
		long userid = StrUtils.getLong(request, "userid", 0);
		int state = StrUtils.getInt(request, "state",0);
		
		RetInfo retInfo = memberService.getMemberList(userid, username,state,pageNum);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<Member> pageVo = (PageVo<Member>) retInfo.getObject();
			pageVo.setCount(memberService.getMemberListByCount(userid, username,state));
			modelMap.put("pageVo", pageVo);
			modelMap.put("DateUtil", DateUtils.class);
			modelMap.put("username", username);
			modelMap.put("state", state);
		}
        return new ModelAndView("/views/manage/member/memberlist", modelMap);
    }
	
	/**
	 * 删除用户--非物理删除
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/delmember",method = RequestMethod.POST)  
    public String deluser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long userid = StrUtils.getLong(request, "id", 0);
		PrintWriter out;
		try {
			out = response.getWriter();
			
			if(memberService.delMember(userid) == 1){
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
	
	
	@RequestMapping(value = "/updateMember", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject updateMember(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		//获取参数
		long userid = StrUtils.getLong(request, "userid");
		String password = StrUtils.getString(request, "password",null);
		
		JSONObject jsonObject = new JSONObject();
		Merchant merchant = merchantService.getMerchantByid(userid);
		Member member =  memberService.getMemberForid(merchant.getUserid());
		if(member != null){
			if(password != null){
				member.setPassword(Md5.md5(password));
			}
			if(memberService.updateMember(member) == 1){
				jsonObject.put("state", "1");
				jsonObject.put("result", "用户更新成功");
			}else{
				jsonObject.put("state", "3");
				jsonObject.put("result", "用户更新失败");
			}
		}else{
			jsonObject.put("state", "2");
			jsonObject.put("result", "用户不存在");
		}
		
		return jsonObject;
	}
	
}
