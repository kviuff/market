package com.market.maicheng.service;

import java.util.List;

import com.market.maicheng.model.BarcodePrice;

public interface BarcodePriceService {
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
