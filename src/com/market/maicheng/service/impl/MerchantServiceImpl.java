package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.CategoryDao;
import com.market.maicheng.dao.MerchantDao;
import com.market.maicheng.model.Merchant;
import com.market.maicheng.service.MerchantService;


@Service("MerchantService")
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired 
    private MerchantDao merchantDao;
	@Autowired 
    private CategoryDao categoryDao;
	@Override
	public int addMerchant(Merchant merchant) {
		// TODO Auto-generated method stub
		return merchantDao.addMerchant(merchant);
	}

	@Override
	public int updateMerchant(Merchant merchant) {
		// TODO Auto-generated method stub
		return merchantDao.updateMerchant(merchant);
	}

	@Override
	public RetInfo getMerchantList(int pageNum,int state,String shopName,long categoryID,long provinceId,long cityId,long regionid,int pagesize) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Merchant> pageVo = new PageVo<Merchant>(pageNum);
		pageVo.setRows(pagesize);
		List<Merchant> list = merchantDao.getMerchantList(state,shopName,categoryID,provinceId,cityId,regionid,pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public Merchant getMerchantByUserid(long userid) {
		return merchantDao.getMerchantByUserid(userid);
	}

	@Override
	public int getMerchantByCount(int state,String shopName,long categoryID,long provinceId,long cityId,long regionid) {
		// TODO Auto-generated method stub
		return merchantDao.getMerchantByCount(state,shopName,categoryID,provinceId,cityId,regionid);
	}

	@Override
	public int changeMerchantState(long id, int state, long audittime) {
		// TODO Auto-generated method stub
		return merchantDao.changeMerchantState(id, state, audittime);
	}

	@Override
	public int changeMerchantRecommend(long id,long updateTime, int recommend) {
		// TODO Auto-generated method stub
		return merchantDao.changeMerchantRecommend(id,updateTime, recommend);
	}

	@Override
	public List<Merchant> getMerchantRecommendList(int limit) {
		// TODO Auto-generated method stub
		return merchantDao.getMerchantRecommendList(limit);
	}

	@Override
	public int updateMerchantInformation(Merchant merchant) {
		// TODO Auto-generated method stub
		return merchantDao.updateMerchantInformation(merchant);
	}

	@Override
	public Merchant getMerchantByid(long id) {
		// TODO Auto-generated method stub
		return merchantDao.getMerchantByid(id);
	}


	
	@Override
	public int updateMerchantCategory(long id, List<Long> cIds) {
		//先删除商家列表
		int state = categoryDao.delMerchantCategoryById(id);
		for(long cId:cIds){
				state = categoryDao.addMerchantCategory(id, cId);
		}
		return state;
	}

	@Override
	public int updateMerchantPubPric(long id, int publicPric) {
		// TODO Auto-generated method stub
		return merchantDao.updateMerchantPubPric(id, publicPric);
	}

}
