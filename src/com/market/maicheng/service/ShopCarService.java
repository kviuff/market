package com.market.maicheng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.market.maicheng.model.ShopCar;


public interface ShopCarService {

	/**
	 * 添加
	 * @param address
	 * @return
	 */
	public int addShopCar(ShopCar car);
	/**
	 * 修改
	 * @param address
	 * @return
	 */
	public int upShopCar(ShopCar car);
	
	/**
	 * 获取列表
	 * @param userid
	 * @return
	 */
	public List<ShopCar> getShopCarList(long memberid);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delShopCar(long memberid);
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public ShopCar getShopCar(long memberid);
	
	public ShopCar getShopCarByid(long id);
	
	/**
	 * 判断是否添加过购物车
	 * @return
	 */
	public ShopCar getShopCarForUseridAndPidids(long mid,long memberid,long pid,long barcodepriceid);
	/**
	 * 判断是否添加过购物车
	 * @return
	 */
	public ShopCar getShopCarForUseridAndMid(long memberid);
	
	/**
	 * 清空购买后的购物车
	 * @param ids
	 * @param pid
	 * @param memberid
	 * @return
	 */
	public int delShopCarForidsAndUserid(String ids,long pid,long memberid);
	
	/**
	 * 更新购物车商品数量
	 * @param carId
	 * @return
	 */
	int updateCarCount(String carId, Integer count);
	
	/**
	 * 更新购物车是否拼单
	 * @param carId
	 * @return
	 */
	int updateCarFight(@Param("carId")String carId, @Param("fight")Integer fight);
}
