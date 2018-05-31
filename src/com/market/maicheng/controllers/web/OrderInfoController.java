package com.market.maicheng.controllers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.IDGenerator;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.common.utils.StrUtils;
import com.market.maicheng.model.Address;
import com.market.maicheng.model.BarcodePrice;
import com.market.maicheng.model.Member;
import com.market.maicheng.model.OrderInfo;
import com.market.maicheng.model.Product;
import com.market.maicheng.model.Relation;
import com.market.maicheng.model.ReportOrder;
import com.market.maicheng.model.ShopCar;
import com.market.maicheng.service.AddressService;
import com.market.maicheng.service.BarcodePriceService;
import com.market.maicheng.service.MemberService;
import com.market.maicheng.service.MerchantService;
import com.market.maicheng.service.OrderInfoService;
import com.market.maicheng.service.ProductService;
import com.market.maicheng.service.RelationService;
import com.market.maicheng.service.ShopCarService;
import com.wechat.WeChatPush;

@Controller
@RequestMapping(value = "/")
public class OrderInfoController {
	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private ProductService productService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private BarcodePriceService barcodePriceService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private ShopCarService shopCarservice;

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private RelationService relationService;

	/**
	 * 添加订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public String addOrder(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			// 有客户id， userid 就是字符串拼接， 没有客户id ， userid 就是登录者id
			// userid 传mid2_saleid1, mid2_saleid2, mid2_0
			// 销售ID
			long saleid = StrUtils.getLong(request, "saleid", 0);
			// 用户ID
			// long userid = StrUtils.getLong(request, "userid", 0);
			String userid = StrUtils.getString(request, "userid");
			// 快递地址ID
			long addressid = StrUtils.getLong(request, "addressid", 0);
			// 订单备注
			String remark = StrUtils.getString(request, "remark");
			// 获取加密参数，参数拼接规则 产品ID&型号ID&购买数量
			String param = new String(Base64.base64ToByteArray(request.getParameter("param")));
			// String param =
			// "201804060329695511&2.0&1&0#!#201804060384957384&55.0&3&0#!#201804060354660255&50.0&2&0";

			if (param.length() == 0) {
				jsonObject.put("state", "0");
				jsonObject.put("result", "参数有误");
				System.out.println("长度===00");
			} else {
				// 订单购买商品拆分结果集
				List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
				// 判断数量标识，有一个商品出问题，整个订单不提交
				boolean flag = true;
				// 判断请求来源 是购物车还是直接提交订单
				if (param.indexOf("#!#") > -1) {
					// 判断购物车流程
					String[] carids = param.split("#!#");
					for (String s : carids) {
						if (flag) {
							setVal(s, jsonObject, flag, resultList);
						} else {
							break;
						}

					}

				} else {
					setVal(param, jsonObject, flag, resultList);
				}
				if (flag && jsonObject.get("state").equals("1")) {
					// 判断用户地址id是否有效
					Address address = addressService.getAddress(addressid);
					if (address == null) {
						jsonObject.put("state", "0");
						jsonObject.put("result", "参数有误");
						System.out.println("地址错误");
					} else {
						Member member = null;
						String saleidStr = String.valueOf(saleid);
						Map<String, String> saleMap = new HashMap<String, String>();
						if (StringUtils.isNotEmpty(saleidStr)) { // 有客户id, userid 就是字符串拼接， userid 传mid2_saleid1,
																	// mid2_saleid2, mid2_0
							String userIdStr = String.valueOf(userid);
							String[] strs = userIdStr.split(",");
							for (String str : strs) {
								String[] mids = str.split("_");
								if (StringUtils.isEmpty(saleMap.get(mids[0]))) {
									saleMap.put(mids[0], mids[1]);
								}
							}
							// 判断登陆状态用户id是否有效
							member = memberService.getMemberForid(Long.valueOf(saleid));
						} else {
							// 判断登陆状态用户id是否有效
							member = memberService.getMemberForid(Long.valueOf(userid));
						}

						if (member == null) {
							jsonObject.put("state", "0");
							jsonObject.put("result", "参数有误");
							System.out.println("用户未登录");
						} else {
							Map<String, String> storeMap = new HashMap<String, String>();
							for (Map<String, Object> remap : resultList) {
								if (StringUtils.isEmpty(storeMap.get(remap.get("mid") + ""))) {
									storeMap.put(remap.get("mid") + "", remap.get("mname") + "");
								}
							}

							Set<String> storeIds = storeMap.keySet();
							List<List<Map<String, Object>>> newList = new ArrayList<List<Map<String, Object>>>();

							for (String mid : storeIds) {
								List<Map<String, Object>> newresultList = new ArrayList<Map<String, Object>>();
								for (Map<String, Object> remap : resultList) {
									if (mid.equals(remap.get("mid") + "")) {
										newresultList.add(remap);
									}
								}
								newList.add(newresultList);
							}

							for (List<Map<String, Object>> resultList1 : newList) {

								long orderid = IDGenerator.getID();
								// 生成主订单
								OrderInfo order = new OrderInfo();
								order.setId(orderid);

								// 添加快递信息
								order.setReceiver(address.getReceiver()); // 收货人
								order.setMobile(address.getMobile()); // 收货联系电话
								order.setProvinceid(address.getProvinceId()); // 省id
								order.setProvincename(address.getProvinceName()); // 省
								order.setCityid(address.getCityId()); // 市id
								order.setCityname(address.getCityName()); // 市
								order.setRegionid(address.getRegionid()); // 区ID
								order.setRegion(address.getRegion()); // 区
								order.setAddress(address.getAddress()); // 详细收货地址
								order.setZipcode(address.getZipcode()); // 邮编
								order.setShippingmethod("上门配送"); // 配送方式
								// 添加订单信息
								// order.setContent(content); //订单备注
								order.setIspay(0); // 是否支付
								order.setPaymethod(-1); // 支付方式
								order.setAddtime(new Date().getTime()); // 下单时间
								order.setDistribution(0D); // 运费
								order.setOrdersource(1); // 订单来源 手机M端
								// 添加主订单标识
								order.setSubid(0L);
								// 添加发票信息
								// order.setInvoiceTypeName(invoiceType); //发票类型
								// order.setInvoiceContent(invoiceContent); //发票抬头
								order.setIsdel(0);
								order.setRemark(remark); // 订单备注
								// 主订单添加成功，添加子订单
								double paymoney = 0D;
								long merchantid = 0;
								
								String productName = "";
								
								if (orderInfoService.insert(order) == 1) {
									// 提交订单之前根据加入购物车的时间先后顺序进行排序
									Collections.sort(resultList1, comparatorByTime);
									for (Map<String, Object> remap : resultList1) {
										Product product = (Product) remap.get("product");
										int buycount = Integer.parseInt(remap.get("buycount").toString());
										double price = Double.parseDouble(remap.get("price").toString());
										BarcodePrice barcodePrice = (BarcodePrice) remap.get("barcodePrice");
										int ispin = Integer.parseInt(remap.get("ispin").toString());
										order = new OrderInfo();
										String pname = product.getTitle();
										productName = productName + pname + ",";
										// 添加子订单
										order.setId(IDGenerator.getID());
										order.setPid(product.getId());
										order.setPcount(buycount);
										order.setPname(pname);
										order.setPurl(product.getImg());
										order.setIsdel(0);
										order.setMerchantid(product.getMerchantID());
										order.setPmoney(price * buycount);
										order.setSubprice(price + ""); // 产品单价
										paymoney += price * buycount;
										order.setSubid(orderid);
										order.setScode(barcodePrice.getScode());
										order.setUnit(barcodePrice.getUnit());
										order.setSpec(barcodePrice.getSpec());
										order.setIspin(ispin);
										order.setIsexamine(0);
										order.setAddtime(new Date().getTime()); // 下单时间
										orderInfoService.insert(order);
										merchantid = product.getMerchantID();
										// 清空购物车
										shopCarservice.delShopCar(Long.parseLong(remap.get("shopcarid").toString()));
									}
									// 订单总价
									order = orderInfoService.selectByPrimaryKey(orderid);
									order.setPmoney(paymoney);
									order.setMerchantid(merchantid);
									order.setMerchantname(merchantService.getMerchantByid(merchantid).getShopName());
									// 有客户id， userid 就是字符串拼接， 没有客户id ， userid 就是登录者id
									// userid 传mid2_saleid1, mid2_saleid2, mid2_0

									if (StringUtils.isNotEmpty(saleidStr)) { // 有客户id, userid 就是字符串拼接， userid
																				// 传mid2_saleid1, mid2_saleid2, mid2_0
										order.setUserid(saleid); // 用户ID
										long newsaleid = Long.valueOf(saleMap.get(merchantid + ""));
										order.setSaleid(newsaleid); // 销售ID
										if (newsaleid != 0) {
											Member sale = memberService.getMemberForid(newsaleid);
											if (sale != null) {
												order.setSaleid(saleid);
												order.setSalename(sale.getRealName());
											}
										}
									} else {
										// 添加用户信息
										order.setUserid(Long.valueOf(userid)); // 用户ID
										order.setSaleid(saleid); // 销售ID
										if (saleid != 0) {
											Member sale = memberService.getMemberForid(saleid);
											if (sale != null) {
												order.setSaleid(saleid);
												order.setSalename(sale.getRealName());
											}
										}
									}

									orderInfoService.updateByPrimaryKey(order);
									
									// 微信推送消息给商户
									Member member1 = memberService.getMemberForid(order.getUserid());
									Map<String, Object> weixinmap = new HashMap<String, Object>();
									weixinmap.put("openId", "");
									weixinmap.put("ordersn", orderid);
									weixinmap.put("ordergoods", productName.substring(0, productName.length() - 1));
									weixinmap.put("orderamount", paymoney + "元");
									weixinmap.put("paymenttype", "");
									weixinmap.put("memberinfo", member1.getRealName() + "," + member1.getMobile());
									weixinmap.put("remark", "请注意查收！");
									WeChatPush.deliverTemplateSendToStoreForOrder(weixinmap);
									
									jsonObject.put("state", "1");
									jsonObject.put("result", "订单添加成功");
									jsonObject.put("data", orderid + "");
								} else {
									jsonObject.put("state", "2");
									jsonObject.put("result", "服务器响应失败，请稍后重试");
								}
							}
						}
					}
				}
			}
			out.print(jsonObject.toString());
			out.close();
		} catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "2");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return null;
	}

	/**
	 * 订单列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getOrderlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String getOrderlist(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;

		// 用户ID
		long id = StrUtils.getLong(request, "id", 0);
		// 查询属性 1 查userid 2 查saleid 3 查merchantid
		int type = StrUtils.getInt(request, "type", 1);

		int pageNum = StrUtils.getInt(request, "pageNum", 1);
		int pageSize = StrUtils.getInt(request, "pagesize", 5);
		long userid = -1;
		long saleid = -1;
		long merchantid = -1;
		if (type == 1) {
			userid = id;
			saleid = -1;
			merchantid = -1;
		} else if (type == 2) {
			userid = -1;
			saleid = id;
			merchantid = -1;
		} else {
			userid = -1;
			saleid = -1;
			merchantid = id;
		}

		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			RetInfo retInfo = orderInfoService.getOrderListByUserid(userid, saleid, merchantid, pageNum, pageSize);
			PageVo<OrderInfo> pageVo = null;
			if (retInfo != null) {
				pageVo = (PageVo<OrderInfo>) retInfo.getObject();
				pageVo.setCount(orderInfoService.getOrderListByUseridForCount(userid, saleid, merchantid));

				// list封装json
				JSONArray jsonArray = new JSONArray();
				JSONObject json;
				JSONArray subArray = new JSONArray();
				JSONObject subjson;
				for (OrderInfo o : pageVo.getList()) {
					json = new JSONObject();
					json.put("id", o.getId());
					json.put("money", o.getPmoney());
					json.put("addtime", o.getAddtime());
					json.put("receiver", o.getReceiver());
					json.put("mobile", o.getMobile());
					json.put("provincename", o.getProvincename());
					json.put("cityname", o.getCityname());
					json.put("region", o.getRegion());
					json.put("address", o.getAddress());
					json.put("zipcode", o.getZipcode());
					json.put("salename", o.getSalename());
					json.put("merchantid", o.getMerchantid());
					json.put("merchantName", o.getMerchantname());
					json.put("remark", o.getRemark());
					if (o.getUserid() > 0 && o.getSaleid() > 0) {
						List<Relation> r = relationService.getRelationListByRelaid(o.getMerchantid(), o.getUserid());
						if (r.size() > 0) {
							json.put("contacts", r.get(0).getContacts());
							json.put("contactTel", r.get(0).getMobile());
						}
					}

					List<OrderInfo> olist = orderInfoService.getOrderListBysubid(o.getId());
					subArray = new JSONArray();
					for (OrderInfo order : olist) {
						subjson = new JSONObject();
						subjson.put("id", order.getId());
						subjson.put("productName", order.getPname());
						subjson.put("count", order.getPcount());
						subjson.put("pic", order.getPurl());
						subjson.put("scode", order.getScode());
						subjson.put("unit", order.getUnit());
						subjson.put("spec", order.getSpec());
						subjson.put("ispin", order.getIspin());
						subjson.put("price", order.getSubprice());
						subjson.put("money", order.getPmoney());
						subArray.add(subjson);
					}
					json.put("subPro", subArray);
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
			out.print(jsonObject.toString());
			out.close();
		} catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "2");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return null;
	}

	// 201709140397147767&201709140342431356&1&0&15
	// productid&barcodepriceid&buycount&ispin&price

	// 201709260640610346&2#!#201709260643256222&20
	private void setVal(String param, JSONObject jsonObject, boolean flag, List<Map<String, Object>> resultList) {
		String[] params = param.split("&");
		ShopCar car = shopCarservice.getShopCarByid(Long.parseLong(params[0]));
		// 产品ID
		long productid = car.getPid();
		// 价格ID
		long barcodepriceid = car.getBarcodepriceid();
		// 选择价格
		double price = Double.parseDouble(params[1]);
		// 购买数量
		int buycount = Integer.parseInt(params[2]);
		// 是否拼单 0不1是
		int ispin = Integer.parseInt(params[3]);
		// 商铺ID
		long mid = car.getMid();
		// 购物车添加时间
		long addtime = car.getAddtime();

		Product product = productService.getProductByID(productid);
		// 判断商品id是否存在
		if (product == null) {
			jsonObject.put("state", "0");
			jsonObject.put("result", "参数有误");
			System.out.println("商品ID不存在");
		} else {
			// 通过barcodepriceid获取产品价格详情
			BarcodePrice barcodePrice = barcodePriceService.getBarcodePriceByid(barcodepriceid);
			if (barcodePrice == null) {
				jsonObject.put("state", "0");
				jsonObject.put("result", "参数有误");
				System.out.println("产品价格详情出错");
			} else {
				if (price != Double.parseDouble(barcodePrice.getPrice())
						|| price != Double.parseDouble(barcodePrice.getStockprice())
						|| price != Double.parseDouble(barcodePrice.getVipprice())) {
					flag = false;
				}
				jsonObject.put("state", "1");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("product", product);
				map.put("buycount", buycount);
				map.put("barcodePrice", barcodePrice);
				map.put("ispin", ispin);
				map.put("price", price);
				map.put("shopcarid", car.getId());
				map.put("mid", mid);
				map.put("mname", car.getMname());
				map.put("addtime", addtime);
				resultList.add(map);
			}
		}
	}

	/**
	 * 订单统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String getReport(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			// 用户ID
			long mid = StrUtils.getLong(request, "mid", 0);
			// 查询属性 1天 2月 3年
			int type = StrUtils.getInt(request, "type", 1);
			List<ReportOrder> reportOrderList = new ArrayList<ReportOrder>();
			if (type == 1) {
				reportOrderList = orderInfoService.reportForDay(mid);
			} else if (type == 2) {
				reportOrderList = orderInfoService.reportForMonth(mid);
			} else {
				reportOrderList = orderInfoService.reportForYear(mid);
			}
			out = response.getWriter();
			if (reportOrderList.size() > 0) {
				jsonObject.put("state", "1");
				jsonObject.put("result", "ok");
				jsonObject.put("data", JSONArray.parseArray(JSON.toJSONString(reportOrderList)));
			} else {
				jsonObject.put("state", "-1");
				jsonObject.put("result", "无数据");
			}
			out.print(jsonObject.toString());
			out.close();
		} catch (Exception e) {
			try {
				out = response.getWriter();
				jsonObject.put("state", "2");
				jsonObject.put("result", "程序出错，请联系管理员");
				out.print(jsonObject.toString());
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return null;
	}

	static Comparator comparatorByTime = new Comparator<Object>() {  
        @Override  
        public int compare(Object o1, Object o2) {
			Map<String, Object> s1 = (Map<String, Object>) o1;
			Map<String, Object> s2 = (Map<String, Object>) o2;

			String s1addtime = s1.get("addtime") + "";
			String s2addtime = s2.get("addtime") + "";
			long s1long = Long.valueOf(s1addtime);
			long s2long = Long.valueOf(s2addtime);

			if (s1long < s2long) {
				return 1;
			}
			return -1;
		}
    };

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addtime", "1523607258000");
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("addtime", "1524471258000");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("addtime", "1518509658000");
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("addtime", "1515831258000");
		
		List<Map<String, Object>> aaaaa = new ArrayList<Map<String,Object>>();
		aaaaa.add(map3);
		aaaaa.add(map2);
		aaaaa.add(map1);
		aaaaa.add(map);
		
		System.out.println("排序前");
		
		for (int i = 0; i < aaaaa.size(); i++) {
			Map<String, Object> mapStr = aaaaa.get(i);
			System.out.println(mapStr.get("addtime"));
		}
		Collections.sort(aaaaa, comparatorByTime);
		System.out.println("排序后");
		for (int i = 0; i < aaaaa.size(); i++) {
			Map<String, Object> mapStr = aaaaa.get(i);
			System.out.println(mapStr.get("addtime"));
		}
		
	}
	
}
