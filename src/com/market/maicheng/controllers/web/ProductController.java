package com.market.maicheng.controllers.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Barcode;
import com.market.maicheng.model.BarcodePrice;
import com.market.maicheng.model.Category;
import com.market.maicheng.model.Product;
import com.market.maicheng.model.SameGood;
import com.market.maicheng.service.BarcodePriceService;
import com.market.maicheng.service.BarcodeService;
import com.market.maicheng.service.CategoryService;
import com.market.maicheng.service.ProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController{
	@Autowired
	private BarcodeService barcodeService;
	
	@Autowired
	private BarcodePriceService barcodePriceService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 根据条形码获取库存信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getProductByBarcode",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject getProductByBarcode(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		String barcodeid = StrUtils.getString(request, "barcodeid","");
//		long mid = StrUtils.getLong(request, "mid", 0);
		JSONObject jsonObject = new JSONObject();
			if(barcodeid.length() > 0){
				//1.获取上架商品
				Product product = productService.getProductByBarcodeID(barcodeid);
				if(product!=null){
					jsonObject.put("dataType", 2);
					jsonObject.put("state", 1);
					jsonObject.put("result", "ok");
					Category category = categoryService.getCategory(product.getCategoryID());
					if(category!=null&&category.getCateName()!=null){
						product.setCategoryName(category.getCateName());
					}
					jsonObject.put("data", product);
				}else{//2.如果不存在，获取条形码库是否存在

					Barcode barcode = barcodeService.getBarcodeForBarcodeid(barcodeid);
					Barcode[] barcodes = {barcode};
					if(barcode == null){
						jsonObject.put("dataType", 0);
						jsonObject.put("state", 2);
						jsonObject.put("result", "条码库存不存在");
					}else{
						barcode.setPriceList(barcodePriceService.getBarcodePriceByBarcodeID(barcode.getId()));
						Category category = categoryService.getCategory(barcode.getCategoryID());
						if(category!=null&&category.getCateName()!=null){
							barcode.setCategoryName(category.getCateName());
						}
						jsonObject.put("dataType", 1);
						jsonObject.put("state", 1);
						jsonObject.put("result", "ok");
						jsonObject.put("data", barcode);
					}
					
				}

			}else{
				jsonObject.put("dataType", 0);
				jsonObject.put("state", 3);
				jsonObject.put("result", "请输入条形码");
			}

		return jsonObject;
	}
	
	
//	/**
//	 * 根据条形码获取库存信息
//	 * @param request
//	 * @param response
//	 * @param modelMap
//	 * @return
//	 */
//	@RequestMapping(value = "/getProductByBarcode",method =  {RequestMethod.GET,RequestMethod.POST})  
//	public String getProductByBarcode(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
//		String barcodeid = StrUtils.getString(request, "barcodeid","");
//		JSONObject jsonObject = new JSONObject();
//		PrintWriter out = null;
//		response.setContentType("text/html;charset=UTF-8");
//		try{
//			out = response.getWriter();
//			if(barcodeid.length() > 0){
//				Barcode barcode = barcodeService.getBarcodeForBarcodeid(barcodeid);
//				if(barcode == null){
//					jsonObject.put("state", 2);
//					jsonObject.put("result", "条码库存不存在");
//				}else{
//					barcode.setPriceList(barcodePriceService.getBarcodePriceByBarcodeID(barcode.getId()));
//					jsonObject.put("state", 1);
//					jsonObject.put("result", "ok");
//					jsonObject.put("data", barcode);
//				}
//			}else{
//				jsonObject.put("state", 3);
//				jsonObject.put("result", "请输入条形码");
//			}
//			out.print(jsonObject.toString());
//			out.close();
//		}catch (Exception e) {
//			try {
//				out = response.getWriter();
//				jsonObject.put("state", 0);
//				jsonObject.put("result", "程序出错,请联系管理员");
//				out.print(jsonObject.toString());
//				out.close();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} 
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 添加条形码库存
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addProductByBarcode",method =  {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject addProductByBarcode(@RequestParam(value="brandname",required = false,defaultValue="")String brandname,
			@RequestParam(value="series",required = false,defaultValue="")String series,
			@RequestParam(value="specs",required = false,defaultValue="")String specs,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		String title = StrUtils.getString(request, "title");			//商品名称
		String barcodeid = StrUtils.getString(request, "barcodeid");	//条形码ID
		long categoryID = StrUtils.getLong(request, "categoryID", 0);		//分类ID
		long brandid = StrUtils.getLong(request, "brandid", 0);			//品牌ID
//		String brandname = StrUtils.getString(request, "brandname");	//品名
//		String series = StrUtils.getString(request, "series");			//系列
//		String specs = StrUtils.getString(request, "specs");			//规格
		String img = request.getParameter("img");					//商品图片
		long createUserID = StrUtils.getLong(request, "userid", 0);		//登陆用户ID
		String barcodePriceStr = StrUtils.getString(request, "barcodeprice");	//价格
		
		
		JSONObject jsonObject = new JSONObject();
			
			if(barcodeid.length() == 0 || categoryID == 0 || brandid == 0 || brandname.length() == 0 || specs.length() == 0 || img.length() == 0 || createUserID == 0){
				jsonObject.put("state", 3);
				jsonObject.put("result", "参数不全");
			}else{
				Barcode barcode = barcodeService.getBarcodeForBarcodeid(barcodeid);
				if(barcode == null){
					long bid = IDGenerator.getID();
					Barcode b = new Barcode();
					b.setId(bid);
					b.setTitle(title);
					b.setBarcodeid(barcodeid);
					b.setCategoryID(categoryID);
					b.setBrandid(brandid);
					b.setBrandname(brandname);
					b.setSeries(series);
					b.setSpecs(specs);
					b.setImg(img);
					b.setCreateTime(new Date().getTime());
					b.setCreateUserID(createUserID);
					
					
					int state = barcodeService.addBarcode(b);
					if(state == 0){
						jsonObject.put("state", 2);
						jsonObject.put("result", "添加失败");
					}else{
						this.setBarcodePrice(barcodePriceStr, bid);
						jsonObject.put("state", 1);
						jsonObject.put("result", "ok");
						jsonObject.put("data", b.getId()+"");
					}
				}else{
					jsonObject.put("state", 1);
					jsonObject.put("result", "该条形码已入库");
					jsonObject.put("data", barcode.getId());
				}
				
			}
		return jsonObject;
	}

	/**
	 * 商品上货
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addProduct",method =  {RequestMethod.GET,RequestMethod.POST}) 
	@ResponseBody
	public JSONObject addProduct(@RequestParam(value="title",required = false,defaultValue="")String title,
			@RequestParam(value="brandname",required = false,defaultValue="")String brandname,
			@RequestParam(value="series",required = false,defaultValue="")String series,
			@RequestParam(value="specs",required = false,defaultValue="")String specs,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){

		
		long barcodeid = StrUtils.getLong(request, "barcodeid"); 		    //条形库存ID
		long categoryID = StrUtils.getLong(request, "categoryID", 0);		//分类ID
//		String title = StrUtils.getString(request, "title");	//品名
		
		String barcodePriceStr = StrUtils.getString(request, "barcodeprice");	//价格
		long merchantID = StrUtils.getLong(request, "merchantID", 0);
		String contentImg = StrUtils.getString(request, "contentImg");
		long brandid = StrUtils.getLong(request, "brandid",0);	//品名
		
//		String brandname = StrUtils.getString(request, "brandname");	//品名
//		String series = StrUtils.getString(request, "series");	//系列
//		String specs = StrUtils.getString(request, "specs");	//规格
		String img = StrUtils.getString(request, "img");		//上架商品图片URL：服务器地址
		JSONObject jsonObject = new JSONObject();
			long productID = IDGenerator.getID();
			Barcode b = barcodeService.getBarcodeForID(barcodeid);
			Product p = null;
			Product product = new Product();
			if(b == null){
				p = productService.getProductByID(barcodeid);
				product = p;
			}else{
				product.setTitle(b.getTitle());
				product.setBarcodeid(b.getBarcodeid());
				product.setBrandid(b.getBrandid());
				product.setSeries(b.getSeries());
				product.setSpecs(b.getSpecs());
				product.setImg(b.getImg());
			}
//			if(!"".equals(brandname)){
//				product.setTitle(brandname);
//			}
//			if(barcodeid!=0){
//				product.setBarcodeid(barcodeid+"");
//			}
			if(!"".equals(title)){
				product.setTitle(title);
			}
			if(brandid!=0){
				product.setBrandid(brandid);
			}
			if(!"".equals(series)&&!"null".equals(series)){
				product.setSeries(series);
			}
			if(!"".equals(specs)){
				product.setSpecs(specs);
			}
			if(!"".equals(img)){
				product.setImg(img);
			}
			product.setId(productID);
			product.setCategoryID(categoryID);
			product.setBrandname(brandname);
			product.setContentImg(contentImg);
			product.setCreateTime(new Date().getTime());
			product.setMerchantID(merchantID);
			product.setIsdel(0);
			product.setState(0);
			product.setHasBarCode(1);
			int state = productService.addProduct(product);
			if(state == 0){
				jsonObject.put("state", 2);
				jsonObject.put("result", "添加失败");
			}else{
				this.setBarcodePrice(barcodePriceStr, productID);
				jsonObject.put("state", 1);
				jsonObject.put("result", "ok");
				jsonObject.put("data", product.getId()+"");
			}
			
		return jsonObject;
	}
	
	/**
	 * 无码商品上架
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addNoCodeProduct",method =  {RequestMethod.GET,RequestMethod.POST}) 
	@ResponseBody
	public JSONObject addNoCodeProduct(@RequestParam(value="title",required = false,defaultValue="")String title,
			@RequestParam(value="brandname",required = false,defaultValue="")String brandname,
			@RequestParam(value="series",required = false,defaultValue="")String series,
			@RequestParam(value="specs",required = false,defaultValue="")String specs,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		
		long categoryID = StrUtils.getLong(request, "categoryID", 0);		//分类ID
//		String title = StrUtils.getString(request, "title");	//商品名称
		long brandid = StrUtils.getLong(request, "brandid",0);	//品名
//		String brandname = StrUtils.getString(request, "brandname");	//品名
//		String series = StrUtils.getString(request, "series");	//系列
//		String specs = StrUtils.getString(request, "specs");	//规格
		String img = StrUtils.getString(request, "img");		//上架商品图片URL：服务器地址
		
		String barcodePriceStr = StrUtils.getString(request, "barcodeprice");	//价格
		long merchantID = StrUtils.getLong(request, "merchantID", 0);
		String contentImg = StrUtils.getString(request, "contentImg");
		
		JSONObject jsonObject = new JSONObject();
			if(!checkPriceStr(barcodePriceStr)){
				jsonObject.put("state", 2);
				jsonObject.put("result", "参数不正确");
			}else{
				long productID = IDGenerator.getID();
				Product product = new Product();
				product.setId(productID);
				product.setTitle(title);
				product.setBarcodeid("");
				product.setCategoryID(categoryID);
				product.setBrandid(brandid);
				product.setBrandname(brandname);
				product.setSeries(series);
				product.setSpecs(specs);
				product.setImg(img);
				product.setContentImg(contentImg);
				product.setCreateTime(new Date().getTime());
				product.setMerchantID(merchantID);
				product.setIsdel(0);
				product.setState(0);
				product.setHasBarCode(0);
				
				int state = productService.addProduct(product);
				if(state == 0){
					jsonObject.put("state", 2);
					jsonObject.put("result", "添加失败");
				}else{
					this.setBarcodePrice(barcodePriceStr, productID);
					jsonObject.put("state", 1);
					jsonObject.put("result", "ok");
					jsonObject.put("data", product.getId()+"");
				}
			}

		return jsonObject;
	}
	
	private boolean checkPriceStr(String barcodePriceStr){
		
		if(barcodePriceStr.indexOf(",") > 0){
			String[] bprice = barcodePriceStr.split(",");
			for(String s : bprice){
				String[] bp = s.split("-");
				if(bp.length<5){
					return false;
				}
			}
		}else{
			String[] bp = barcodePriceStr.split("-");
			if(bp.length<5){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 产品置顶
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/setProductTop", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject getCustomerApplyList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		long productID = StrUtils.getLong(request, "productID");			//商品ID
		int istop = StrUtils.getInt(request, "istop");			//商品ID
		JSONObject jsonObject = new JSONObject();
		int state = productService.updateProductTop(productID, istop);
		if(state == 1){
			jsonObject.put("state", "1");
			jsonObject.put("result", "设置成功");
		}else{
			jsonObject.put("state", "2");
			jsonObject.put("result", "设置失败");
		}
		return jsonObject;
	}
	
	
	
	/**
	 * 修改商品
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/upProduct",method =  {RequestMethod.GET,RequestMethod.POST}) 
	@ResponseBody
	public JSONObject upProduct(@RequestParam(value="title",required = false,defaultValue="")String title,
			@RequestParam(value="brandname",required = false,defaultValue="")String brandname,
			@RequestParam(value="series",required = false,defaultValue="")String series,
			@RequestParam(value="specs",required = false,defaultValue="")String specs,HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		long productID = StrUtils.getLong(request, "productID");			//商品ID
		long barcodeid = StrUtils.getLong(request, "barcodeid", 0); 		    //条形库存ID
		long categoryID = StrUtils.getLong(request, "categoryID", 0);		//分类ID
//		String brandname = StrUtils.getString(request, "brandname");	//品名
		long brandid = StrUtils.getLong(request, "brandid",0);	//品牌id
		
		String barcodePriceStr = StrUtils.getString(request, "barcodeprice");	//价格
		String contentImg = StrUtils.getString(request, "contentImg");
//		String specs = StrUtils.getString(request, "specs","");
		
//		String series = StrUtils.getString(request, "series","");
		String img = StrUtils.getString(request, "img","");
//		String title = StrUtils.getString(request, "title","");
		
		JSONObject jsonObject = new JSONObject();
			Product product = productService.getProductByID(productID);
			if(barcodeid != 0){
				Barcode b = barcodeService.getBarcodeForID(barcodeid);
				product.setTitle(b.getTitle());
				product.setBarcodeid(b.getBarcodeid());
				product.setBrandid(b.getBrandid());
				product.setSeries(b.getSeries());
				product.setSpecs(b.getSpecs());
				product.setImg(b.getImg());
			}
			if(brandid!=0){
				product.setBrandid(brandid);
			}
			if(!"".equals(series)){
				product.setSeries(series);
			}
			if(!"".equals(img)){
				product.setImg(img);
			}
			if(!"".equals(title)){
				product.setTitle(title);
			}
			if(!"".equals(specs)){
				product.setSpecs(specs);
			}

//			if(barcodeid!=0){
//				product.setBarcodeid(barcodeid+"");
//			}

			product.setCategoryID(categoryID);
			product.setBrandname(brandname);
			product.setContentImg(contentImg);
			product.setIsdel(0);
			this.upBarcodePrice(barcodePriceStr, productID);
			int state = productService.updateProduct(product);
			if(state == 0){
				jsonObject.put("state", 2);
				jsonObject.put("result", "修改失败");
			}else{
				jsonObject.put("state", 1);
				jsonObject.put("result", "ok");
			}
			
		return jsonObject;
	}
	
	/**
	 * 删除上架商品
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/delProduct",method =  {RequestMethod.GET,RequestMethod.POST}) 
	@ResponseBody
	public JSONObject delProduct(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		JSONObject jsonObject = new JSONObject();
		long pid = StrUtils.getLong(request, "pid");
		
			int state = productService.delProduct(pid);
			if(state == 0){
				jsonObject.put("state", 2);
				jsonObject.put("result", "删除失败");
			}else{
				jsonObject.put("state", 1);
				jsonObject.put("result", "ok");
			}
			
		return jsonObject;
	}
	
	/**
	 * 添加价格
	 * @param barcodePriceStr  单位-规格-进价-会员价-供货商价,....
	 * @param bid				库存或产品ID
	 */
	private void setBarcodePrice(String barcodePriceStr,long bid){
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
			barcodePriceService.addBarcodePrice(barcodePrice);
		}
	}
	
	/**
	 * 修改价格
	 * @param barcodePriceStr  id-单位-规格-进价-会员价-供货商价,....
	 * @param bid				库存或产品ID
	 */
	private void upBarcodePrice(String barcodePriceStr, long productID){
		BarcodePrice barcodePrice;
		if(barcodePriceStr.indexOf(",") > 0){
			String[] bprice = barcodePriceStr.split(",");
			for(String s : bprice){
				String[] bp = s.split("-");
				if (StringUtils.isEmpty(bp[0]) || "null".equals(bp[0])) {
					barcodePrice = new BarcodePrice();
					barcodePrice.setId(IDGenerator.getID());
					barcodePrice.setScode(bp[1]);
					barcodePrice.setUnit(bp[2]);
					barcodePrice.setSpec(bp[3]);
					barcodePrice.setPrice(bp[4]);
					barcodePrice.setVipprice(bp[5]);
					barcodePrice.setStockprice(bp[6]);
					barcodePrice.setBarcodeid(productID);
					barcodePriceService.addBarcodePrice(barcodePrice);
				} else {
					barcodePrice = barcodePriceService.getBarcodePriceByid(Long.parseLong(bp[0]));
					barcodePrice.setScode(bp[1]);
					barcodePrice.setUnit(bp[2]);
					barcodePrice.setSpec(bp[3]);
					barcodePrice.setPrice(bp[4]);
					barcodePrice.setVipprice(bp[5]);
					barcodePrice.setStockprice(bp[6]);
					barcodePriceService.updateBarcodePrice(barcodePrice);
				}
			}
		}else{
			String[] bp = barcodePriceStr.split("-");
			
			if (StringUtils.isEmpty(bp[0])  || "null".equals(bp[0])) {
				barcodePrice = new BarcodePrice();
				barcodePrice.setId(IDGenerator.getID());
				barcodePrice.setScode(bp[1]);
				barcodePrice.setUnit(bp[2]);
				barcodePrice.setSpec(bp[3]);
				barcodePrice.setPrice(bp[4]);
				barcodePrice.setVipprice(bp[5]);
				barcodePrice.setStockprice(bp[6]);
				barcodePrice.setBarcodeid(productID);
				barcodePriceService.addBarcodePrice(barcodePrice);
			} else {
				barcodePrice = barcodePriceService.getBarcodePriceByid(Long.parseLong(bp[0]));
				barcodePrice.setScode(bp[1]);
				barcodePrice.setUnit(bp[2]);
				barcodePrice.setSpec(bp[3]);
				barcodePrice.setPrice(bp[4]);
				barcodePrice.setVipprice(bp[5]);
				barcodePrice.setStockprice(bp[6]);
				barcodePriceService.updateBarcodePrice(barcodePrice);
			}
		}
	}
	
	
//	/**
//	 * 获取商户商品列表
//	 * @param request
//	 * @param response
//	 * @param modelMap
//	 * @return
//	 */
//	@RequestMapping(value = "/getMerchantProductList", method = {RequestMethod.GET,RequestMethod.POST})  
//	public String getMerchantList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
//		int pageNum = StrUtils.getInt(request, "p",1);
//		int pagesize = StrUtils.getInt(request, "pagesize",Constants.PAGESIZE);
//		long merchantID = StrUtils.getLong(request, "merchantID");
//		long categoryID = StrUtils.getLong(request, "categoryID",-1);
//		String title = StrUtils.getString(request, "title", null);					//商品名称
//		String barcodeid = StrUtils.getString(request, "barcodeid", null);			//商品条形码
//		
//		//排序属性1、品牌 2、发布时间
//		int type = StrUtils.getInt(request, "type");
//		String orderby = "order by id asc";
//		switch (type) {
//		case 1:
//			orderby = "order by brandid desc";
//			break;
//		case 2:
//			orderby = "order by createTime desc";
//			break;
//
//		default:
//			orderby = "order by id asc";
//			break;
//		}
//		JSONObject jsonObject = new JSONObject();
//		PrintWriter out = null;
//		response.setContentType("text/html;charset=UTF-8");
//		
//		try{
//			out = response.getWriter();
//			JSONArray jsonArray = new JSONArray();
//			JSONObject json;
//			
//			RetInfo retInfo = productService.getProductListForPage(pageNum,merchantID,categoryID,barcodeid,title,orderby,pagesize);
//			if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
//				PageVo<Product> pageVo = (PageVo<Product>) retInfo.getObject();
//				pageVo.setCount(productService.getProductListByCount(merchantID,categoryID,barcodeid,title,orderby));
//				
//				for(Product p : pageVo.getList()){
//					json = new JSONObject();
//					json.put("id", p.getId());
//					json.put("title", p.getTitle());
//					json.put("barcodeid", p.getBarcodeid());
//					json.put("categoryID", p.getCategoryID());
//					json.put("brandid", p.getBrandid());
//					json.put("brandname", p.getBrandname());
//					json.put("series", p.getSeries());
//					json.put("specs", p.getSpecs());
//					json.put("img", p.getImg());
//					json.put("contentImg", p.getContentImg());
//					List<BarcodePrice> bplist = barcodePriceService.getBarcodePriceByBarcodeID(p.getId());
//					JSONArray array = new JSONArray();
//					JSONObject json1;
//					if(bplist.size() >= 1){
//						for(BarcodePrice bp : bplist){
//							json1 = new JSONObject();
//							json1.put("id", bp.getId());
//							json1.put("scode", bp.getScode());			//条码
//							json1.put("unit", bp.getUnit());			//单位
//							json1.put("spec", bp.getSpec());			//规格
//							json1.put("price", bp.getPrice());			//进价
//							json1.put("vipprice", bp.getVipprice());	//会员价
//							json1.put("stockprice", bp.getStockprice());//供货商价
//							array.add(json1);
//						}
//					}
//					json.put("priceList", array);
//					jsonArray.add(json);
//				}
//				jsonObject.put("state", 1);
//				jsonObject.put("result", "ok");
//				jsonObject.put("data", jsonArray);
//				jsonObject.put("pageNum", pageVo.getPageNum());
//				jsonObject.put("pageCount", pageVo.getPageCount());
//			}else{
//				jsonObject.put("state", 2);
//				jsonObject.put("result", "无数据");
//				
//			}
//			out.print(jsonObject.toString());
//			out.close();
//		}catch (Exception e) {
//			try {
//				out = response.getWriter();
//				jsonObject.put("state", 0);
//				jsonObject.put("result", "程序出错,请联系管理员");
//				out.print(jsonObject.toString());
//				out.close();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} 
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 扫描获取商品及同品商品列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/sanCodeGetProduct", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject searchMerchantByCodeId(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		String barcodeId = StrUtils.getString(request, "barcodeid", null);			//商品条形码
		if(barcodeId==null){
			jsonObject.put("state", 2);
			jsonObject.put("result", "无条形码");
			return jsonObject;
			
		}

		Product p = productService.getProductByBarcodeID(barcodeId);
		if(p==null){
			jsonObject.put("state", 2);
			jsonObject.put("result", "无数据");
			return jsonObject;
		}
		json = createProductInfo1(p);
		json.put("isSameGoods", false);
		jsonArray.add(json);
//		jsonArray.addAll(getSameGoods(p.getBarcodeid()));
//		json.put("sameGoods", getSameGoods(p.getBarcodeid()));
//		Barcode barcode = barcodeService.getBarcodeForBarcodeid(code);
//		if(barcode == null){
//			jsonObject.put("state", 2);
//			jsonObject.put("result", "无数据");
//			return jsonObject;
//		}
		List<Product> sameGoods = productService.getSameGoods(p.getMerchantID(), -1, barcodeId, null);
		for(Product item : sameGoods){
			json = createProductInfo(item);
			json.put("isSameGoods", true);
			jsonArray.add(json);
		}

		jsonObject.put("state", 1);
		jsonObject.put("result", "ok");
		jsonObject.put("data", jsonArray);
		return jsonObject;
		
	}
	
	private JSONObject createProductInfo(Product p){
		JSONObject json = new JSONObject();
		json.put("id", p.getId());
		json.put("title", p.getTitle());
		json.put("barcodeid", p.getBarcodeid());
		json.put("categoryID", p.getCategoryID());
//		String categoryName = "";
//		Category category = categoryService.getCategory(p.getCategoryID());
//		if(category!=null&&category.getCateName()!=null){
//			categoryName = category.getCateName();
//		}
		json.put("categoryName", p.getCategoryName());
		json.put("brandid", p.getBrandid());
		json.put("brandname", p.getBrandname());
		json.put("title", p.getTitle());
		json.put("series", p.getSeries());
		json.put("specs", p.getSpecs());
		json.put("istop", p.getIstop());
		json.put("img", p.getImg());
		json.put("contentImg", p.getContentImg());
		
//		long barcodeId = p.getBarcodeid()!=null?Long.parseLong(p.getBarcodeid()):0; 
		//List<BarcodePrice> bplist = barcodePriceService.getBarcodePriceByBarcodeID(p.getId());
		List<BarcodePrice> bplist = p.getBarcodePriceList();
		JSONArray array = new JSONArray();
		JSONObject json1;
		if(bplist!=null&&bplist.size() >= 1){
			for(BarcodePrice bp : bplist){
				json1 = new JSONObject();
				json1.put("id", bp.getId());
				json1.put("scode", bp.getScode());			//条码
				json1.put("unit", bp.getUnit());			//单位
				json1.put("spec", bp.getSpec());			//规格
				json1.put("price", bp.getPrice());			//进价
				json1.put("vipprice", bp.getVipprice());	//会员价
				json1.put("stockprice", bp.getStockprice());//供货商价
				array.add(json1);
			}
		}
		json.put("priceList", array);
		return json;
	}
	
	
	/**
	 * 获取商户商品列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @author 高红飞 
	 * @return
	 */
	@RequestMapping(value = "/searchMerchantProductList", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject searchMerchantList(@RequestParam(value="title",required = false,defaultValue="")String title,
			HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		int pageNum = StrUtils.getInt(request, "p",1);
		int pagesize = StrUtils.getInt(request, "pagesize",Constants.PAGESIZE);
		long merchantID = StrUtils.getLong(request, "merchantID");
		long categoryID = StrUtils.getLong(request, "categoryID",-1);
//		String title = StrUtils.getString(request, "title", null);					//商品名称
		if("".equals(title)){
			title = null;
		}
		String barcodeid = StrUtils.getString(request, "barcodeid", null);			//商品条形码
		
		//排序属性1、品牌 2、发布时间
		int type = StrUtils.getInt(request, "type");
		String orderby = "order by a.id asc";
		switch (type) {
		case 1:
			orderby = "order by a.brandid desc";
			break;
		case 2:
			orderby = "order by a.createTime desc";
			break;

		default:
			orderby = "order by a.id asc";
			break;
		}
		long aaa = System.currentTimeMillis();
		List<Product> sameGood = productService.getSameGoods(merchantID, categoryID, barcodeid, title);
		
		//不分页处理
		if(pagesize == -1){
			
			if (StringUtils.isNotEmpty(title)) { // 名称不搜索同品
				//（不带同品）
				List<Product> list = productService.getProducts(merchantID, categoryID, barcodeid, title, orderby);
				if(list==null){
					jsonObject.put("state", 2);
					jsonObject.put("result", "无数据");
					return jsonObject;
				}
				for(Product p : list){
					json = createProductInfo(p);
					jsonArray.add(json);
				}
				jsonObject.put("state", 1);
				jsonObject.put("result", "ok");
				jsonObject.put("data", jsonArray);
				long bbb = System.currentTimeMillis();
				System.out.println("用时333333========" + (bbb-aaa));
				return jsonObject; 
			} 
			if (StringUtils.isNotEmpty(barcodeid)) { // 条码搜索同品
				List<Product> list = productService.getProductsHasSg(merchantID, categoryID, barcodeid, title, orderby);
				if(list==null){
					jsonObject.put("state", 2);
					jsonObject.put("result", "无数据");
					return jsonObject;
				}
				for(Product p : list){
					json = createProductInfo(p);
					jsonArray.add(json);
				}
				jsonObject.put("state", 1);
				jsonObject.put("result", "ok");
				jsonObject.put("data", jsonArray);
				long bbb = System.currentTimeMillis();
				System.out.println("用时44444444========" + (bbb-aaa));
				return jsonObject; 
			}
			
			//分页处理
//			if(sameGood==null||sameGood.isEmpty()){
//				
//				//（不带同品）
//				List<Product> list = productService.getProducts(merchantID, categoryID, barcodeid, title, orderby);
//				if(list==null){
//					jsonObject.put("state", 2);
//					jsonObject.put("result", "无数据");
//					return jsonObject;
//				}
//				for(Product p : list){
//					json = createProductInfo(p);
//					jsonArray.add(json);
//				}
//				jsonObject.put("state", 1);
//				jsonObject.put("result", "ok");
//				jsonObject.put("data", jsonArray);
//				long bbb = System.currentTimeMillis();
//				System.out.println("用时333333========" + (bbb-aaa));
//				return jsonObject; 
//			}else{
//				List<Product> list = productService.getProductsHasSg(merchantID, categoryID, barcodeid, title, orderby);
//				if(list==null){
//					jsonObject.put("state", 2);
//					jsonObject.put("result", "无数据");
//					return jsonObject;
//				}
//				for(Product p : list){
//					json = createProductInfo(p);
//					jsonArray.add(json);
//				}
//				jsonObject.put("state", 1);
//				jsonObject.put("result", "ok");
//				jsonObject.put("data", jsonArray);
//				long bbb = System.currentTimeMillis();
//				System.out.println("用时44444444========" + (bbb-aaa));
//				return jsonObject; 
//			}
			
		} else {
			if (StringUtils.isNotEmpty(title)) { // 名称不搜索同品
				return getMerchantProductForPage(title,pageNum,merchantID,categoryID,barcodeid,orderby,pagesize);
			} else { // 条码搜索同品
				RetInfo retInfo = productService.getProductsHasSgForPage(pageNum,merchantID,categoryID,barcodeid,title,orderby,pagesize);
				if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
					PageVo<Product> pageVo = (PageVo<Product>) retInfo.getObject();
					pageVo.setCount(productService.getProductsSgByCount(merchantID,categoryID,barcodeid,title,orderby));
					
					for(Product p : pageVo.getList()){
						json = createProductInfo(p);
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
				long bbb = System.currentTimeMillis();
				System.out.println("用时55555555========" + (bbb-aaa));
				return jsonObject;
			}
		}
		
		return jsonObject; 
		
		//分页处理
//		if(sameGood==null||sameGood.isEmpty()){
//			//（不带同品）
//			return getMerchantProductForPage(title,pageNum,merchantID,categoryID,barcodeid,orderby,pagesize); 
//		}
//		//（带同品）
//
//			
//		RetInfo retInfo = productService.getProductsHasSgForPage(pageNum,merchantID,categoryID,barcodeid,title,orderby,pagesize);
//		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
//			PageVo<Product> pageVo = (PageVo<Product>) retInfo.getObject();
//			pageVo.setCount(productService.getProductsSgByCount(merchantID,categoryID,barcodeid,title,orderby));
//			
//			for(Product p : pageVo.getList()){
//				json = createProductInfo(p);
//				jsonArray.add(json);
//			}
//			jsonObject.put("state", 1);
//			jsonObject.put("result", "ok");
//			jsonObject.put("data", jsonArray);
//			jsonObject.put("pageNum", pageVo.getPageNum());
//			jsonObject.put("pageCount", pageVo.getPageCount());
//		}else{
//			jsonObject.put("state", 2);
//			jsonObject.put("result", "无数据");
//			
//		}
//		long bbb = System.currentTimeMillis();
//		System.out.println("用时55555555========" + (bbb-aaa));
//		return jsonObject;
	}
	private JSONObject getMerchantProductForPage(@RequestParam(value="title",required = false,defaultValue="")String title,int pageNum,long merchantID,long categoryID,String barcodeid,String orderby,int pagesize){
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		long aaa = System.currentTimeMillis();
		RetInfo retInfo = productService.getProductsForPage(pageNum,merchantID,categoryID,barcodeid,title,orderby,pagesize);
		if(retInfo != null && retInfo.getResult() == Constants.SUCCESS_RESULT){
			PageVo<Product> pageVo = (PageVo<Product>) retInfo.getObject();
			pageVo.setCount(productService.getProductsForPageByCount(merchantID,categoryID,barcodeid,title,orderby));
			
			for(Product p : pageVo.getList()){
				json = createProductInfo(p);
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
		long bbbbb = System.currentTimeMillis();
		System.out.println("用时22222222222222222===========" + (bbbbb - aaa));
		return jsonObject;
	}
	/**
	 * 获取商户商品列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @author 高红飞 
	 * @return
	 */
	@RequestMapping(value = "/getMerchantProductList", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject getMerchantList(@RequestParam(value="title",required = false,defaultValue="")String title,
			HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		long aaa = System.currentTimeMillis();
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		int pageNum = StrUtils.getInt(request, "p",1);
		int pagesize = StrUtils.getInt(request, "pagesize",Constants.PAGESIZE);
		long merchantID = StrUtils.getLong(request, "merchantID");
		long categoryID = StrUtils.getLong(request, "categoryID",-1);
//		String title = StrUtils.getString(request, "title", null);					//商品名称
		String barcodeid = StrUtils.getString(request, "barcodeid", null);			//商品条形码
		
		//排序属性1、品牌 2、发布时间
		int type = StrUtils.getInt(request, "type");
		String orderby = "order by a.id asc";
		switch (type) {
		case 1:
			orderby = "order by a.brandid desc";
			break;
		case 2:
			orderby = "order by a.createTime desc";
			break;

		default:
			orderby = "order by a.id asc";
			break;
		}
		if(pageNum==-1){
			List<Product> list = productService.getProducts(merchantID, categoryID, barcodeid, title, orderby);
			if(list==null){
				jsonObject.put("state", 2);
				jsonObject.put("result", "无数据");
				return jsonObject;
			}
			for(Product p : list){
				json = createProductInfo(p);
				jsonArray.add(json);
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", jsonArray);
			long bbbbb = System.currentTimeMillis();
			System.out.println("用时11111111111111111===========" + (bbbbb - aaa));
			return jsonObject;
		}else{
			return getMerchantProductForPage(title,pageNum,merchantID,categoryID,barcodeid,orderby,pagesize);
		}

		
	}
	
	
	@RequestMapping(value = "/getProductPrice", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject getProductPrice(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		
		long productID = StrUtils.getLong(request, "productID",-1);
		JSONObject jsonObject = new JSONObject();
			List<BarcodePrice> bplist = barcodePriceService.getBarcodePriceByBarcodeID(productID);
			JSONArray json = new JSONArray();
			json.addAll(bplist);
			json.listIterator();
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			jsonObject.put("data", json);
		return jsonObject;
	}
	/**
	 * 设置同类
	 * @param barcodeId1
	 * @param barcodeId2
	 * @param isAdd 0:删除关系，1：添加关系
	 * @return
	 */
	@RequestMapping(value = "/setSameGoods", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject setSameGoods(@RequestParam(value="mid",required = true,defaultValue="0")long mId,
			@RequestParam(value="barcodeId",required = true,defaultValue="")String barcodeId,
			@RequestParam(value="sameGoodIds",required=true,defaultValue="")String sameGoodIds,
			@RequestParam(value="isAdd",required=true,defaultValue="1")int isAdd){
			JSONObject jsonObject = new JSONObject();
			int result = 0;
			String sid = "";
			JSONArray ids = JSONArray.parseArray(sameGoodIds);
			if(mId==0){
				jsonObject.put("state", 3);
				jsonObject.put("result", "参数不对");
				return jsonObject;
			}
			SameGood sameGood = productService.getSameGood(mId, barcodeId);
			if(sameGood!=null){
				sid = sameGood.getSid();
				result = productService.deleteSameGoods(barcodeId,sid);
				
			}else{
				sameGood = new SameGood();
				sameGood.setBarcodeId(barcodeId);
				sameGood.setMid(mId);
				sid = java.util.UUID.randomUUID().toString().replaceAll("-", "");
				sameGood.setSid(sid);
				result = productService.addSameGood(sameGood);
			}
			if(result==0){
				jsonObject.put("state", 2);
				jsonObject.put("result", "设置失败");
				return jsonObject;
			}
			for(int i=0;i<ids.size();i++){
				result = productService.setSameGoods(sid,mId,ids.get(i).toString());
			}
			jsonObject.put("state", 1);
			jsonObject.put("result", "ok");
			return jsonObject;
	}
	
	/**
	 * 编辑商品时移除已保存过的属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delBarcodePrice", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public JSONObject delBarcodePrice(@RequestParam(value="id",required = true,defaultValue="0")long id){
			JSONObject jsonObject = new JSONObject();
			int result = 0;
			result = barcodePriceService.delBarcodePrice(id);
			if (result == 0) {
				jsonObject.put("state", 2);
				jsonObject.put("result", "移除失败");
			} else {
				jsonObject.put("state", 1);
				jsonObject.put("result", "ok");
			}
			return jsonObject;
	}
	
	private JSONObject createProductInfo1(Product p){
		JSONObject json = new JSONObject();
		json.put("id", p.getId());
		json.put("title", p.getTitle());
		json.put("barcodeid", p.getBarcodeid());
		json.put("categoryID", p.getCategoryID());
		String categoryName = "";
		Category category = categoryService.getCategory(p.getCategoryID());
		if(category!=null&&category.getCateName()!=null){
			categoryName = category.getCateName();
		}
		json.put("categoryName", categoryName);
		json.put("brandid", p.getBrandid());
		json.put("brandname", p.getBrandname());
		json.put("series", p.getSeries());
		json.put("specs", p.getSpecs());
		json.put("istop", p.getIstop());
		json.put("img", p.getImg());
		json.put("contentImg", p.getContentImg());
		
//		long barcodeId = p.getBarcodeid()!=null?Long.parseLong(p.getBarcodeid()):0; 
		List<BarcodePrice> bplist = barcodePriceService.getBarcodePriceByBarcodeID(p.getId());
		JSONArray array = new JSONArray();
		JSONObject json1;
		if(bplist!=null&&bplist.size() >= 1){
			for(BarcodePrice bp : bplist){
				json1 = new JSONObject();
				json1.put("id", bp.getId());
				json1.put("scode", bp.getScode());			//条码
				json1.put("unit", bp.getUnit());			//单位
				json1.put("spec", bp.getSpec());			//规格
				json1.put("price", bp.getPrice());			//进价
				json1.put("vipprice", bp.getVipprice());	//会员价
				json1.put("stockprice", bp.getStockprice());//供货商价
				array.add(json1);
			}
		}
		json.put("priceList", array);
		return json;
	}
	
}
