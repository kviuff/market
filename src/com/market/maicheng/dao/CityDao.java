package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.City;



@Repository
public interface CityDao {
	/**
	 * 获取会员列表
	 * @param id
	 * @param username
	 * @return
	 */
	public List<City> getCityByPid(@Param("Pid")long Pid);
	
	public int addCity(City city);
}
