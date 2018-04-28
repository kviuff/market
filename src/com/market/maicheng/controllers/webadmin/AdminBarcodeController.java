package com.market.maicheng.controllers.webadmin;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.controllers.web.BaseController;
import com.market.maicheng.model.Barcode;
import com.market.maicheng.model.BarcodePrice;
import com.market.maicheng.model.Brand;
import com.market.maicheng.model.Category;
import com.market.maicheng.service.BarcodePriceService;
import com.market.maicheng.service.BarcodeService;
import com.market.maicheng.service.BrandService;
import com.market.maicheng.service.CategoryService;

/**
 * 条形码库存后台控制
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/webadmin/barcode")
public class AdminBarcodeController extends BaseController{
	@Autowired
	private BarcodeService barcodeService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BarcodePriceService barcodePriceService;
	
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})  
    public ModelAndView barcodelist(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		int pageNum = StrUtils.getInt(request, "p",1);
		String brandName = StrUtils.getString(request, "brandname",null);	//品名
		RetInfo retInfo = barcodeService.getBarcodeList(brandName,pageNum);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<Barcode> pageVo = (PageVo<Barcode>) retInfo.getObject();
			pageVo.setCount(barcodeService.getBarcodeListByCount(brandName));
			modelMap.put("pageVo", pageVo);
			modelMap.put("DateUtil", DateUtils.class);
			modelMap.put("brandname", brandName);
		}
		return new ModelAndView("/views/manage/barcode/list", modelMap);
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)  
    public String barcodeadd(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		//产品分类
		List<Category> cateList = categoryService.getProductCategoryList(0, -1,0);
		modelMap.put("cateList", cateList);
		//品牌
		List<Brand> brandlist = brandService.getBrandList();
		modelMap.put("brandlist", brandlist);
		return "/views/manage/barcode/add";
	}
	
	@RequestMapping(value = "/toadd",method = RequestMethod.POST)  
    public String barcodetoadd(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		String title = StrUtils.getString(request, "title");			//商品名称
		String barcodeid = StrUtils.getString(request, "barcodeid");	//条形码ID
		String cateids = StrUtils.getString(request, "cateids");		//分类ID
		long brandid = StrUtils.getLong(request, "brand", 0);			//品牌ID
		String brandname = StrUtils.getString(request, "brandname");	//品名
		String series = StrUtils.getString(request, "series");			//系列
		String specs = StrUtils.getString(request, "specs");			//规格
		String img = request.getParameter("img");				//商品图片
		String barcodePriceStr = StrUtils.getString(request, "barcodePrice",""); //产品价格字符串
		
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		long bid = IDGenerator.getID();
		try{
			out = response.getWriter();
			
			if(title.length() == 0 || barcodeid.length() == 0 || cateids.length() == 0 || brandid == 0 || brandname.length() == 0 || series.length() == 0 || specs.length() == 0 || img.length() == 0 ){
				jsonObject.put("state", 3);
				jsonObject.put("result", "参数不全");
			}else{
				Barcode b = new Barcode();
				b.setId(bid);
				b.setTitle(title);
				b.setBarcodeid(barcodeid);
				b.setCategoryID(Long.parseLong(cateids));
				b.setBrandid(brandid);
				b.setBrandname(brandname);
				b.setSeries(series);
				b.setSpecs(specs);
				b.setImg(img);
				b.setCreateTime(new Date().getTime());
				b.setCreateUserID(0);
				
				
				int state = barcodeService.addBarcode(b);
				if(state == 0){
					jsonObject.put("state", 2);
					jsonObject.put("result", "添加失败");
				}else{
					BarcodePrice barcodePrice;
					if(barcodePriceStr.indexOf(",") > 0){
						String[] bprice = barcodePriceStr.split(",");
						for(String s : bprice){
							String[] bp = s.split("-");
							barcodePrice = new BarcodePrice();
							barcodePrice.setId(IDGenerator.getID());
							barcodePrice.setBarcodeid(bid);
							barcodePrice.setScode(bp[0]);
							barcodePrice.setUnit(bp[1]);
							barcodePrice.setSpec(bp[2]);
							barcodePrice.setPrice(bp[3]);
							barcodePrice.setVipprice(bp[4]);
							barcodePrice.setStockprice(bp[5]);
							barcodePrice.setIsDel(0);
							barcodePriceService.addBarcodePrice(barcodePrice);
						}
					}else{
						String[] bp = barcodePriceStr.split("-");
						barcodePrice = new BarcodePrice();
						barcodePrice.setId(IDGenerator.getID());
						barcodePrice.setBarcodeid(bid);
						barcodePrice.setScode(bp[0]);
						barcodePrice.setUnit(bp[1]);
						barcodePrice.setSpec(bp[2]);
						barcodePrice.setPrice(bp[3]);
						barcodePrice.setVipprice(bp[4]);
						barcodePrice.setStockprice(bp[5]);
						barcodePrice.setIsDel(0);
						barcodePriceService.addBarcodePrice(barcodePrice);
					}
					jsonObject.put("state", 1);
					jsonObject.put("result", "ok");
					jsonObject.put("data", b.getId());
				}
			}
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
	
	@RequestMapping(value = "/delpro",method = RequestMethod.POST)  
	@ResponseBody
    public int delbrand(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		long id = StrUtils.getLong(request, "id");
		if(id==0){
			return 0;
		}
		
		return barcodeService.delBarcode(id);
	}
}
