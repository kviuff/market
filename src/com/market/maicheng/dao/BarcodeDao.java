package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Barcode;

@Repository
public interface BarcodeDao {
	/**
	 * 添加条形码库存
	 * @param barcode
	 * @return
	 */
	public int addBarcode(Barcode barcode);
	
	/**
	 * 修改条形码库存
	 * @param barcode
	 * @return
	 */
	public int updateBarcode(Barcode barcode);
	
	/**
	 * 删除条形码库存
	 * @param id
	 * @return
	 */
	public int delBarcode(long id);
	
	/**
	 * 根据ID获取条形码库存
	 * @param id
	 * @return
	 */
	public Barcode getBarcodeForID(long id);
	
	/**
	 * 根据条形码获取库存
	 * @param barcodeid
	 * @return
	 */
	public Barcode getBarcodeForBarcodeid(String barcodeid);
	
	/**
	 * 获取条码库存列表
	 * @param pageNum
	 * @return
	 */
	public List<Barcode> getBarcodeList(@Param("brandName") String brandName,@Param("offset") long offset, @Param("rows") long rows);
	
	/**
	 * 获取条码库存数量
	 * @param id
	 * @param username
	 * @return
	 */
	public int getBarcodeListByCount(@Param("brandName") String brandName);
}
