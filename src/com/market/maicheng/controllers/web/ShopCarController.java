package com.market.maicheng.controllers.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.BarcodePrice;
import com.market.maicheng.model.Product;
import com.market.maicheng.model.ShopCar;
import com.market.maicheng.model.ShopCarApi;
import com.market.maicheng.model.ShopCarVo;
import com.market.maicheng.service.BarcodePriceService;
import com.market.maicheng.service.ProductService;
import com.market.maicheng.service.ShopCarService;

@Controller
public class ShopCarController extends BaseController {
	@Autowired
	private ShopCarService shopCarservice;

	@Autowired
	private BarcodePriceService barcodePriceService;

	@Autowired
	private ProductService productService;

	/**
	 * 添加购物车
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addCar", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject addCar(HttpServletRequest request, HttpServletResponse response) {
		long userid = StrUtils.getLong(request, "userid", 0); // 用户ID
		long pid = StrUtils.getLong(request, "pid"); // 产品ID
		long mid = StrUtils.getLong(request, "mid"); // 商户ID
		JSONArray paramList = JSONArray.parseArray(request.getParameter("param"));
		Product p = productService.getProductByID(pid);
		if (mid == 0 && p != null) {
			mid = p.getMerchantID();
		}

		JSONObject jsonObject = new JSONObject();
		if (userid != 0) {
			for (int i = 0; i < paramList.size(); i++) {
				long barcodepriceid = paramList.getJSONObject(i).getLongValue("barcodepriceid"); // 价格表id
				int count = paramList.getJSONObject(i).getIntValue("count");
				int ping = paramList.getJSONObject(i).getIntValue("ping");

				// ShopCar car = shopCarservice.getShopCarForUseridAndMid(userid);
				// if(car!=null&&mid!= car.getMid()){
				// jsonObject.put("state", "2");
				// jsonObject.put("result", "不是同一商家的产品不能添加到购物车");
				// return jsonObject;
				// }

				ShopCar car = shopCarservice.getShopCarForUseridAndPidids(mid, userid, pid, barcodepriceid);
				if (car == null) {
					car = new ShopCar();
					car.setId(IDGenerator.getID());
					car.setMid(mid);
					car.setMemberid(userid);
					car.setAddtime(new Date().getTime());
					car.setCount(count);
					car.setBarcodepriceid(barcodepriceid);
					car.setPid(pid);
					car.setPing(ping);
					shopCarservice.addShopCar(car);
				} else {
					car.setCount(car.getCount() + count);
					shopCarservice.upShopCar(car);

				}
			}
			jsonObject.put("state", "1");
			jsonObject.put("result", "成功");
		} else {
			jsonObject.put("state", "-1");
			jsonObject.put("result", "用户未登陆");
		}

		return jsonObject;
	}

	@RequestMapping(value = "/getShopCatList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject memCar(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		long userid = StrUtils.getLong(request, "userid", 0);
		if (userid != 0) {
			List<ShopCar> list = shopCarservice.getShopCarList(userid); // 获取用户购物车下所有的商品
			
			//拆单,将购物车数据存入map,键为店铺id,值为店铺名称,得到所有店铺的信息(唯一,去重)
			Map<Long,String> storeMap = new HashMap<Long,String>();
			for(ShopCar cart:list){
				if(cart!=null){
					if (StringUtils.isEmpty(storeMap.get(cart.getMid()))) {
						storeMap.put(cart.getMid(), cart.getMname());
					}
				}
			}
			
			Set<Long> storeIds = storeMap.keySet();
			
			List<ShopCarVo> carList = new ArrayList<ShopCarVo>();
			for (Long mid : storeIds) {
				ShopCarVo shopCarVo = new ShopCarVo();
				shopCarVo.setMid(mid);
				shopCarVo.setMname(storeMap.get(mid));
				//创建一个新的Cart集合
				List<ShopCarApi> listNew=new ArrayList<ShopCarApi>();
				for (ShopCar s : list) {
					if (s.getMid() == mid) {
						ShopCarApi shopCarApi = new ShopCarApi();
						Product p = productService.getProductByID(s.getPid());
						BarcodePrice bp = barcodePriceService.getBarcodePriceByid(s.getBarcodepriceid());
						shopCarApi.setId(s.getId());
						shopCarApi.setMid(mid);
						shopCarApi.setImg(p.getImg());
						shopCarApi.setSpec(bp.getSpec());
						shopCarApi.setCount(s.getCount());
						shopCarApi.setBrandname(p.getBrandname());
						shopCarApi.setPrice(bp.getPrice());
						shopCarApi.setVipprice(bp.getVipprice());
						shopCarApi.setStockprice(bp.getStockprice());
						shopCarApi.setPing(s.getPing());
						shopCarApi.setGoodsname(p.getTitle());
						shopCarApi.setAddtime(DateUtils.getLongToDateTime(s.getAddtime()));
						listNew.add(shopCarApi);
					}
				}
				shopCarVo.setCarList(listNew);
				carList.add(shopCarVo);
			}
			
//			JSONArray jsonlist = new JSONArray();
//			JSONObject json = null;
//			for (ShopCar s : list) {
//				json = new JSONObject();
//				Product p = productService.getProductByID(s.getPid());
//				BarcodePrice bp = barcodePriceService.getBarcodePriceByid(s.getBarcodepriceid());
//				json.put("id", s.getId()); // 购物车ID
//				json.put("mid", p.getMerchantID()); // 商家id
//				json.put("img", p.getImg()); // 商品图片
//				json.put("spec", bp.getSpec()); // 规格
//				json.put("count", s.getCount()); // 购买数量
//				json.put("brandname", p.getBrandname()); // 品名
//				json.put("price", bp.getPrice()); // 进价
//				json.put("vipprice", bp.getVipprice()); // 会员价
//				json.put("stockprice", bp.getStockprice()); // 供货商价
//				json.put("ping", s.getPing()); // 是否拼单 0不拼
//				jsonlist.add(json);
//			}
			jsonObject.put("state", "1");
			jsonObject.put("result", "ok");
			jsonObject.put("data", JSONArray.parseArray(JSON.toJSONString(carList)));

		} else {
			jsonObject.put("state", "-1");
			jsonObject.put("result", "用户未登陆");
		}
		return jsonObject;

	}

	/**
	 * 删除某个购物车
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delCar", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject delCar(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		// long id = StrUtils.getLong(request, "id",0);
		JSONArray paramList = JSONArray.parseArray(request.getParameter("param"));

		for (int i = 0; i < paramList.size(); i++) {
			long id = paramList.getJSONObject(i).getLongValue("id"); // 购物车id
			shopCarservice.delShopCar(id);
		}
		jsonObject.put("state", "1");
		jsonObject.put("result", "删除成功");
		return jsonObject;
	}

	public static void main(String[] args) {
		JSONObject json = new JSONObject();

		json.put("barcodepriceid", "201709140342431356"); // 价格表id
		json.put("count", 1);
		json.put("ping", 0);
		JSONArray ja = new JSONArray();
		ja.add(json);
		System.out.println(ja.toString());

	}

	/**
	 * 更新购物车商品数量
	 */
	@RequestMapping(value = "/updateCarCount", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject updateCarCount(@RequestParam(value = "count", defaultValue = "1") Integer count,
			@RequestParam(value = "carId") String cartId, @RequestParam(value = "userid") String userid) {

		JSONObject jsonObject = new JSONObject();
		if (StringUtils.isNotEmpty(userid)) {
			shopCarservice.updateCarCount(cartId, count);
			jsonObject.put("state", "1");
			jsonObject.put("result", "成功");
		} else {
			jsonObject.put("state", "-1");
			jsonObject.put("result", "用户未登陆");
		}

		return jsonObject;
	}

	/**
	 * 更新购物车是否拼单
	 */
	@RequestMapping(value = "/updateCarFight", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JSONObject updateCarFight(@RequestParam(value = "fight", defaultValue = "1") Integer fight,
			@RequestParam(value = "carId") String cartId, @RequestParam(value = "userid") String userid) {

		JSONObject jsonObject = new JSONObject();
		if (StringUtils.isNotEmpty(userid)) {
			shopCarservice.updateCarFight(cartId, fight);
			jsonObject.put("state", "1");
			jsonObject.put("result", "成功");
		} else {
			jsonObject.put("state", "-1");
			jsonObject.put("result", "用户未登陆");
		}

		return jsonObject;
	}
}
