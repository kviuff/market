package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.dao.ShopCarDao;
import com.market.maicheng.model.ShopCar;
import com.market.maicheng.service.ShopCarService;

@Service("ShopCarService")
public class ShopCarServiceImpl implements ShopCarService {

	@Autowired
	private ShopCarDao shoopingcardao;
	@Override
	public int addShopCar(ShopCar car) {
		// TODO Auto-generated method stub
		return shoopingcardao.addShopCar(car);
	}

	@Override
	public int upShopCar(ShopCar car) {
		// TODO Auto-generated method stub
		return shoopingcardao.upShopCar(car);
	}

	@Override
	public List<ShopCar> getShopCarList(long memberid) {
		// TODO Auto-generated method stub
		return shoopingcardao.getShopCarList(memberid);
	}

	@Override
	public int delShopCar(long id) {
		// TODO Auto-generated method stub
		return shoopingcardao.delShopCar(id);
	}

	@Override
	public ShopCar getShopCar(long id) {
		// TODO Auto-generated method stub
		return shoopingcardao.getShopCar(id);
	}

	@Override
	public ShopCar getShopCarForUseridAndPidids(long mid,long memberid, long pid, long barcodepriceid) {
		// TODO Auto-generated method stub
		return shoopingcardao.getShopCarForUseridAndPidids(mid,memberid, pid, barcodepriceid);
	}

	@Override
	public int delShopCarForidsAndUserid(String ids, long pid, long memberid) {
		// TODO Auto-generated method stub
		return shoopingcardao.delShopCarForidsAndUserid(ids, pid, memberid);
	}

	@Override
	public ShopCar getShopCarByid(long id) {
		// TODO Auto-generated method stub
		return shoopingcardao.getShopCarByid(id);
	}

	@Override
	public ShopCar getShopCarForUseridAndMid(long memberid) {
		// TODO Auto-generated method stub
		return shoopingcardao.getShopCarForUseridAndMid( memberid);
	}

	/**
	 * 更新购物车商品数量
	 * @param carId
	 * @return
	 */
	public int updateCarCount(String carId, Integer count) {
		return shoopingcardao.updateCarCount(carId, count);
	}

	/**
	 * 更新购物车是否拼单
	 * @param carId
	 * @return
	 */
	public int updateCarFight(String carId, Integer fight) {
		return shoopingcardao.updateCarFight(carId, fight);
	}

	
}
