package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.FavoriteDao;
import com.market.maicheng.model.Favorite;
import com.market.maicheng.service.FavoriteService;
@Service("FavoriteService")
public class FavoriteServiceImpl implements FavoriteService {
	@Autowired
	private FavoriteDao favoriteDao;
	@Override
	public RetInfo getList(long createid, long classid, int pageNum, int pagesize) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Favorite> pageVo = new PageVo<Favorite>(pageNum);
		pageVo.setRows(pagesize);
		List<Favorite> list = favoriteDao.getList(createid,classid, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getListForCount(long createid, long classid) {
		return favoriteDao.getListForCount(createid, classid);
	}

	@Override
	public boolean isExist(long createid, long mid) {
		Favorite favorite = favoriteDao.getFavoriteByKey(createid, mid);
		return favorite!=null&&favorite.getMid()>0;
	}

	@Override
	public int insert(Favorite record) {
		return favoriteDao.insert(record);
	}

	@Override
	public int delete(long id) {
		return favoriteDao.deleteByPrimaryKey(id);
	}

}
