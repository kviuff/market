package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.dao.AdvertDao;
import com.market.maicheng.model.Advert;
import com.market.maicheng.service.AdvertService;

@Service("AdvertService")
public class AdvertServiceImpl implements AdvertService {

	@Autowired
	private AdvertDao advertDao;
	
	@Override
	public int addAdvert(Advert advert) {
		return advertDao.addAdvert(advert);
	}

	@Override
	public int upAdvert(Advert advert) {
		return advertDao.upAdvert(advert);
	}

	@Override
	public List<Advert> getAdvert() {
		return advertDao.getAdvert();
	}

	@Override
	public List<Advert> getAdvertForType(long type) {
		return advertDao.getAdvertForType(type);
	}

	@Override
	public Advert getAdvertForID(long id) {
		return advertDao.getAdvertForID(id);
	}

	@Override
	public int delAdvertForID(long id) {
		return advertDao.delAdvertForID(id);
	}

}
