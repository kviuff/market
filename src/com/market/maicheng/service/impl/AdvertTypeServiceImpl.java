package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.dao.AdvertTypeDao;
import com.market.maicheng.model.AdvertType;
import com.market.maicheng.service.AdvertTypeService;


@Service("AdvertTypeService")
public class AdvertTypeServiceImpl implements AdvertTypeService {
	@Autowired
	private AdvertTypeDao advertTypeDao;
	
	@Override
	public int addAdvertType(AdvertType advertType) {
		return advertTypeDao.addAdvertType(advertType);
	}

	@Override
	public int upAdvertType(AdvertType advertType) {
		return advertTypeDao.upAdvertType(advertType);
	}

	@Override
	public List<AdvertType> getAdvertType() {
		return advertTypeDao.getAdvertType();
	}

	@Override
	public int delAdvertType(long id) {
		return advertTypeDao.delAdvertType(id);
	}

	@Override
	public AdvertType getAdvertTypeByID(long id) {
		return advertTypeDao.getAdvertTypeByID(id);
	}

}
