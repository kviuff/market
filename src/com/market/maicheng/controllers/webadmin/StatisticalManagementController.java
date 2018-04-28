package com.market.maicheng.controllers.webadmin;

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
import com.market.maicheng.model.StatisticsOrder;
import com.market.maicheng.service.OrderInfoService;

@Controller
@RequestMapping(value = "/webadmin/staticticalManagerment")
public class StatisticalManagementController {
	
	@Autowired
	private OrderInfoService orderInfoService;
	
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

}
