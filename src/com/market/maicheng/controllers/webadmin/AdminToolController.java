package com.market.maicheng.controllers.webadmin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.controllers.web.BaseController;
import com.market.maicheng.model.AbGoods;
import com.market.maicheng.model.Barcode;
import com.market.maicheng.service.AbGoodsService;
import com.market.maicheng.service.BarcodeService;
@Controller
@RequestMapping(value = "/webadmin/tool")
public class AdminToolController extends BaseController{
	@Autowired
	private AbGoodsService abGoodsService;
	
	@Autowired
	private BarcodeService barcodeService;
//	
//	@Autowired
//	private BarcodePriceService barcodePriceService;
//	
//	@Autowired
//	private BrandService brandService;
	/**
	 * 同步数据-页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/syndata",method = RequestMethod.GET)  
    public ModelAndView adduser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
        return new ModelAndView("/views/manage/tool/syndata", modelMap);
    }
	
	
	
	@RequestMapping(value = "/todo",method = RequestMethod.POST)  
	@ResponseBody
    public int todo(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		int unInsertCount = 0;
		int insertCount = 0;
		boolean isStart = true;
		int count = abGoodsService.getAbGoodssCount();
		int page = (int)Math.ceil(count/100);
		for(int i=0;i<page;i++){
			List<AbGoods> list = abGoodsService.getAbGoodss((i)*100,100);
			for(AbGoods adGoods:list){
//				if(adGoods.getGoodsBarcode().equals("6914790010085")){
//					isStart = true;
//				}
				if(proline(adGoods,isStart)){
					insertCount++;
				}else{
					unInsertCount++;
				}
			}
		}
		
		modelMap.put("insertCount", insertCount);
		modelMap.put("unInsertCount", unInsertCount);
		return 1;
	}
	
	
	private boolean proline(AbGoods adGoods,boolean isStart){
		if(!isStart){
			return false;
		}
		Barcode barcode = barcodeService.getBarcodeForBarcodeid(adGoods.getGoodsBarcode());
		if(barcode == null){
			long bid = IDGenerator.getID();
			Barcode b = new Barcode();
			b.setId(bid);
			b.setTitle(adGoods.getGoodsName());
			b.setBarcodeid(adGoods.getGoodsBarcode());
			b.setCategoryID(201712260855224744L);
			b.setBrandid(adGoods.getBrandId());
//			Brand brand = brandService.getBrand(adGoods.getBrandId());
//			if(brand!=null){
//				b.setBrandname(brand.getBrandName());
//			}
			b.setBrandname(adGoods.getBrandName());
			b.setSpecs(adGoods.getTgItem());
			b.setSeries("");
//			if(adGoods.getGoodsSpec()!=null&&!"".equals(adGoods.getGoodsSpec())){
//				b.setSpecs(adGoods.getGoodsSpec());
//			}
			b.setImg("");
			b.setCreateTime(new Date().getTime());
			b.setCreateUserID(88888);
			
			
			int state = barcodeService.addBarcode(b);
			return state>0;
		}
		return false;
	}

}
