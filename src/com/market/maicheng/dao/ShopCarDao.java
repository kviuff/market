package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.ShopCar;


@Repository
public interface ShopCarDao {
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
	public List<ShopCar> getShopCarList(@Param("memberid")long memberid);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delShopCar(@Param("id")long id);
	
	public int delShopCarForidsAndUserid(@Param("ids")String ids,@Param("pid")long pid,@Param("memberid")long memberid);
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public ShopCar getShopCar(@Param("memberid")long memberid);
	
	public ShopCar getShopCarByid(long id);
	
	public ShopCar getShopCarForUseridAndMid(@Param("memberid")long memberid);
	
	
	/**
	 * 判断是否添加过购物车
	 * @return
	 */
	public ShopCar getShopCarForUseridAndPidids(@Param("mid")long mid,@Param("memberid")long memberid,@Param("pid")long pid,@Param("barcodepriceid")long barcodepriceid);
	
	/**
	 * 更新购物车商品数量
	 * @param carId
	 * @return
	 */
	int updateCarCount(@Param("carId")String carId, @Param("count")Integer count);
	
	/**
	 * 更新购物车是否拼单
	 * @param carId
	 * @return
	 */
	int updateCarFight(@Param("carId")String carId, @Param("fight")Integer fight);
}
