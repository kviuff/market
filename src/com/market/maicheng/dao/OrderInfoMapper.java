package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.OrderInfo;
import com.market.maicheng.model.ReportOrder;
import com.market.maicheng.model.StatisticsOrder;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
    
    List<OrderInfo> getOrderListByUserid(@Param("userid")long userid,@Param("saleid")long saleid,@Param("merchantid")long merchantid,@Param("offset")int offset,@Param("rows")int rows);
    
    int getOrderListByUseridForCount(@Param("userid")long userid,@Param("saleid")long saleid,@Param("merchantid")long merchantid);
    
    List<OrderInfo> getOrderListBysubid(@Param("subid")long subid);
    
    List<StatisticsOrder> getOrderListByMerchant(@Param("offset")int offset,@Param("rows")int rows);
    
    int getOrderListByMerchantCount();
    
    /**
	 * 根据商铺ID查询订单列表
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
    List<OrderInfo> getOrderListByMerchantId(@Param("merchantid")long merchantid,@Param("offset")int offset,@Param("rows")int rows);
	
	/**
	 * 根据商铺ID查询订单列表-总条数
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	int getOrderListByMerchantIdForCount(@Param("merchantid")long merchantid);
	
	/**
	 * 根据店铺ID查询所有订单
	 * @param merchantid
	 * @return
	 */
	List<OrderInfo> getOrderListBymerchantid(@Param("merchantid")long merchantid);
	
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