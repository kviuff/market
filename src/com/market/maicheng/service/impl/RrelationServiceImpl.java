package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.RelationMapper;
import com.market.maicheng.model.Relation;
import com.market.maicheng.service.RelationService;

@Service("RelationService")
public class RrelationServiceImpl implements RelationService {
	
	@Autowired
	RelationMapper relationDao;

	@Override
	public int insert(Relation record) {
		// TODO Auto-generated method stub
		return relationDao.insert(record);
	}

	@Override
	public Relation selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return relationDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Relation record) {
		// TODO Auto-generated method stub
		return relationDao.updateByPrimaryKey(record);
	}

	@Override
	public RetInfo getRelationListByCreateid(long mid,long createid,long classid, int pageNum,
			int pagesize) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Relation> pageVo = new PageVo<Relation>(pageNum);
		pageVo.setRows(pagesize);
		List<Relation> list = relationDao.getRelationListByCreateid(mid,createid,classid, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getRelationListByCreateidForCount(long mid,long createid,long classid) {
		// TODO Auto-generated method stub
		return relationDao.getRelationListByCreateidForCount(mid,createid,classid);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return relationDao.deleteByPrimaryKey(id); 
	}

	@Override
	public int getRelationListByRelaidForCount(long mid,
			long relaid) {
		// TODO Auto-generated method stub
		return relationDao.getRelationListByRelaidForCount(mid,relaid);
	}

	@Override
	public List<Relation> getRelationListByRelaid(long mid,
			long relaid) {
		// TODO Auto-generated method stub
		return relationDao.getRelationListByRelaid(mid, relaid);
	}

	@Override
	public Integer getMerchantPricLevel(long relaid, long mid) {
		Relation relation = relationDao.getMerchantByKey(relaid, mid);
		return (relation!=null&&relation.getPricLevel()>0)?relation.getPricLevel():0;
	}

	@Override
	public int updatePricLevel(long mid, long relaid, int pricLevel) {
		// TODO Auto-generated method stub
		return relationDao.updatePricLevel(mid, relaid, pricLevel);
	}

	@Override
	public int getRelationListByLevelForCount(long mid, int pricLevel) {
		// TODO Auto-generated method stub
		return relationDao.getRelationListByLevelForCount(mid ,pricLevel);
	}
	
	@Override
	public RetInfo getRelationListByLevel(long mid, int pricLevel, int pageNum,int pagesize) {
		// TODO Auto-generated method stub
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Relation> pageVo = new PageVo<Relation>(pageNum);
		pageVo.setRows(pagesize);
		List<Relation> list = relationDao.getRelationListByLevel(mid,pricLevel, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}
	
	
	@Override
	public int getCustomerListForCount(long mid) {
		// TODO Auto-generated method stub
		return relationDao.getCustomerListForCount(mid );
	}
	
	@Override
	public RetInfo getCustomerList(long mid, int pageNum,int pagesize) {
		// TODO Auto-generated method stub
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Relation> pageVo = new PageVo<Relation>(pageNum);
		pageVo.setRows(pagesize);
		List<Relation> list = relationDao.getCustomerList(mid, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public boolean isExistRelation(long relaid, long mid) {
		Relation relation = relationDao.getMerchantByKey(relaid, mid);
		return relation!=null&&relation.getMid()>0;
	}

}
