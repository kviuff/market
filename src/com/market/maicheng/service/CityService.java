package com.market.maicheng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.market.maicheng.model.City;

public interface CityService {

	/**
	 * 获取会员列表
	 * @param id
	 * @param username
	 * @return
	 */
	public List<City> getCityByPid(long Pid);
	
	public int addCity(City city);
}
