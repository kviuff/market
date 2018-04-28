package com.market.maicheng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.OrderInfo;
import com.market.maicheng.model.ReportOrder;
import com.market.maicheng.model.StatisticsOrder;

public interface OrderInfoService {
	
	public int insert(OrderInfo record);
	
	public OrderInfo selectByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(OrderInfo record);
	
	public RetInfo getOrderListByUserid(long userid, long saleid,long merchantid,int pageNum,int pagesize);
	
	public int getOrderListByUseridForCount(long userid, long saleid,long merchantid);
	
	List<OrderInfo> getOrderListBysubid(long subid);
	
	/**
	 * 统计店铺下的订单销量列表
	 * @return
	 */
	RetInfo getOrderListByMerchant(int pageNum,int pagesize);
	
	/**
	 * 统计店铺下的订单销量列表-总条数
	 * @return
	 */
	int getOrderListByMerchantCount();
	
	/**
	 * 根据商铺ID查询订单列表
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	RetInfo getOrderListByMerchantId(long merchantid,int pageNum,int pagesize);
	
	/**
	 * 根据商铺ID查询订单列表-总条数
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	int getOrderListByMerchantIdForCount(long merchantid);
	
	/**
	 * 根据店铺ID查询所有订单
	 * @param merchantid
	 * @return
	 */
	List<OrderInfo> getOrderListBymerchantid(long merchantid);
	
	/**
	 * 订单天统计
	 * @param merchantid
	 * @return
	 */
	List<ReportOrder> reportForDay (@Param("merchantid")long merchantid);
	
	/**
	 * 订单月统计
	 * @param merchantid
	 * @return
	 */
	List<ReportOrder> reportForMonth (@Param("merchantid")long merchantid);
	
	/**
	 * 订单年统计
	 * @param merchantid
	 * @return
	 */
	List<ReportOrder> reportForYear (@Param("merchantid")long merchantid);
	
}
