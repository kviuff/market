package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.OrderInfoMapper;
import com.market.maicheng.model.OrderInfo;
import com.market.maicheng.model.ReportOrder;
import com.market.maicheng.model.StatisticsOrder;
import com.market.maicheng.service.OrderInfoService;


@Service("OrderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
	@Autowired
	OrderInfoMapper orderInfoDao;
	
	@Override
	public int insert(OrderInfo record) {
		// TODO Auto-generated method stub
		return orderInfoDao.insert(record);
	}

	@Override
	public OrderInfo selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return orderInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(OrderInfo record) {
		// TODO Auto-generated method stub
		return orderInfoDao.updateByPrimaryKey(record);
	}

	@Override
	public RetInfo getOrderListByUserid(long userid, long saleid,long merchantid,int pageNum, int pagesize) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<OrderInfo> pageVo = new PageVo<OrderInfo>(pageNum);
		pageVo.setRows(pagesize);
		List<OrderInfo> list = orderInfoDao.getOrderListByUserid(userid,saleid,merchantid , pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getOrderListByUseridForCount(long userid, long saleid,long merchantid) {
		// TODO Auto-generated method stub
		return orderInfoDao.getOrderListByUseridForCount(userid,saleid,merchantid);
	}

	@Override
	public List<OrderInfo> getOrderListBysubid(long subid) {
		// TODO Auto-generated method stub
		return orderInfoDao.getOrderListBysubid(subid);
	}

	/**
	 * 统计店铺下的订单销量列表
	 * @return
	 */
	public RetInfo getOrderListByMerchant(int pageNum,int pagesize) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<StatisticsOrder> pageVo = new PageVo<StatisticsOrder>(pageNum);
		pageVo.setRows(pagesize);
		List<StatisticsOrder> list = orderInfoDao.getOrderListByMerchant(pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}
	
	/**
	 * 统计店铺下的订单销量列表-总条数
	 * @return
	 */
	public int getOrderListByMerchantCount() {
		return orderInfoDao.getOrderListByMerchantCount();
	}

	/**
	 * 根据商铺ID查询订单列表
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	public RetInfo getOrderListByMerchantId(long merchantid, int pageNum, int pagesize) {
		
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<OrderInfo> pageVo = new PageVo<OrderInfo>(pageNum);
		pageVo.setRows(pagesize);
		List<OrderInfo> list = orderInfoDao.getOrderListByMerchantId(merchantid , pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	/**
	 * 根据商铺ID查询订单列表-总条数
	 * @param merchantid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	public int getOrderListByMerchantIdForCount(long merchantid) {
		return orderInfoDao.getOrderListByMerchantIdForCount(merchantid);
	}

	/**
	 * 根据店铺ID查询所有订单
	 * @param merchantid
	 * @return
	 */
	public List<OrderInfo> getOrderListBymerchantid(long merchantid) {
		return orderInfoDao.getOrderListBymerchantid(merchantid);
	}

	@Override
	public List<ReportOrder> reportForDay(long merchantid) {
		return orderInfoDao.reportForDay(merchantid);
	}

	@Override
	public List<ReportOrder> reportForMonth(long merchantid) {
		return orderInfoDao.reportForMonth(merchantid);
	}

	@Override
	public List<ReportOrder> reportForYear(long merchantid) {
		return orderInfoDao.reportForYear(merchantid);
	}
	
	
	
	
	
	
	

}
