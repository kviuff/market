package com.market.maicheng.service;

import java.util.List;


import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.Relation;

public interface RelationService {
	public int insert(Relation record);
	
	public int delete(Long id);
	
	public Relation selectByPrimaryKey(Long id);
	
	public boolean isExistRelation(long relaid,long mid);
	
	Integer getMerchantPricLevel(long relaid,long mid);
	
	public int getRelationListByLevelForCount(long mid,int pricLevel);
	
	public RetInfo getRelationListByLevel(long mid,int pricLevel, int pageNum,int pagesize);
	
	
	public int getCustomerListForCount(long mid);
	
	public RetInfo getCustomerList(long mid, int pageNum,int pagesize);
	
	
	
	
	public int updatePricLevel(long mid,long relaid,int pricLevel);
	public int updateByPrimaryKey(Relation record);
	
	public RetInfo getRelationListByCreateid(long mid,long createid,long classid,int pageNum,int pagesize);
	
	public int getRelationListByCreateidForCount(long mid,long createid,long classid);
	
	public int getRelationListByRelaidForCount(long mid,long relaid);
	
	public List<Relation> getRelationListByRelaid(long mid,long relaid);
	
}
