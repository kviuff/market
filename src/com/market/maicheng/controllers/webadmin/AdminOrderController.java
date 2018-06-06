package com.market.maicheng.controllers.webadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.EnumConstant;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Member;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.model.OrderInfo;
import com.market.maicheng.model.StatisticsOrder;
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;
import com.market.maicheng.service.OrderInfoService;

@Controller
@RequestMapping(value = "/webadmin/order")
public class AdminOrderController {
	
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private MemberService memberService;
	
	/**
	 * 管理员用户列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})  
    public ModelAndView userlist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		int pageNum = StrUtils.getInt(request, "p",1);
			
		RetInfo retInfo = orderInfoService.getOrderListByMerchant(pageNum, 20);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<StatisticsOrder> pageVo = (PageVo<StatisticsOrder>) retInfo.getObject();
			pageVo.setCount(orderInfoService.getOrderListByMerchantCount());
			modelMap.put("pageVo", pageVo);
			modelMap.put("DateUtil", DateUtils.class);
			modelMap.put("EnumConstant", EnumConstant.class);
		}
        return new ModelAndView("/views/manage/statistics/saleslist", modelMap);
    }
	
	/**
	 * 根据商铺ID查询订单列表
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/orderListByMechantId",method = {RequestMethod.GET,RequestMethod.POST})  
    public ModelAndView orderListByMechantId(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		Long mechantId = StrUtils.getLong(request, "mechantid", 0);
			
		List<OrderInfo> orderInfoList = orderInfoService.getOrderListBymerchantid(mechantId);
		if(orderInfoList != null && orderInfoList.size() > 0){
			modelMap.put("orderInfoList", orderInfoList);
			modelMap.put("DateUtil", DateUtils.class);
		}
        return new ModelAndView("/views/manage/merchant/order_list", modelMap);
    }
	
	/**
	 * 根据商铺ID查询订单列表
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/orderListBySubId",method = {RequestMethod.GET,RequestMethod.POST})  
    public ModelAndView orderListBySubId(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		Long subid = StrUtils.getLong(request, "subid", 0);
			
		OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(subid);
		long merchantId = orderInfo.getMerchantid();
		Merchant merchant = merchantService.getMerchantByid(merchantId);
		
		Member member = memberService.getMemberForid(orderInfo.getUserid());
		
		List<OrderInfo> orderInfoList = orderInfoService.getOrderListBysubid(subid);
		if(orderInfoList != null && orderInfoList.size() > 0){
			modelMap.put("orderInfoList", orderInfoList);
			modelMap.put("DateUtil", DateUtils.class);
		}
		modelMap.put("orderInfo", orderInfo);
		modelMap.put("merchant", merchant);
		modelMap.put("member", member);
        return new ModelAndView("/views/manage/merchant/print", modelMap);
    }

}
