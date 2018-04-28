package com.market.maicheng.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.market.maicheng.model.BarcodePrice;

/**
 * 条码库存价格
 * @author Administrator
 *
 */
@Repository
public interface BarcodePriceDao {
	/**
	 * 添加
	 * @param barcodePrice
	 * @return
	 */
	public int addBarcodePrice(BarcodePrice barcodePrice);
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public BarcodePrice getBarcodePriceByid(long id);
	
	/**
	 * 修改
	 * @param barcodePrice
	 * @return
	 */
	public int updateBarcodePrice(BarcodePrice barcodePrice);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delBarcodePrice(long id);
	
	/**
	 * 获取价格列表
	 * @param barcodeid
	 * @return
	 */
	public List<BarcodePrice> getBarcodePriceByBarcodeID(long barcodeid);
}
