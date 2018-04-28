package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.dao.CityDao;
import com.market.maicheng.model.City;
import com.market.maicheng.service.CityService;

@Service("CityService")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao citydao;
	@Override
	public List<City> getCityByPid(long Pid) {
		return citydao.getCityByPid(Pid);
	}
	@Override
	public int addCity(City city) {
		// TODO Auto-generated method stub
		return citydao.addCity(city);
	}

}
