package com.market.maicheng.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.dao.AbGoodsDao;
import com.market.maicheng.model.AbGoods;
import com.market.maicheng.service.AbGoodsService;
@Service("AbGoodsService")
public class AbGoodsServiceImpl implements AbGoodsService {
	@Autowired
	private AbGoodsDao abGoodsDao;

	@Override
	public List<AbGoods> getAbGoodss(long offset, long rows) {
		return abGoodsDao.getAbGoodss(offset,rows);
	}

	@Override
	public int getAbGoodssCount() {
		return abGoodsDao.getAbGoodssCount();
	}
}
