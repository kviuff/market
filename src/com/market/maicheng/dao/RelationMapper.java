package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Relation;


@Repository
public interface RelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Long id);
    
    int getRelationListByLevelForCount(@Param("mid")long mid,@Param("pricLevel")int pricLevel);
    
    List<Relation> getRelationListByLevel(@Param("mid")long mid,@Param("pricLevel")int pricLevel,@Param("offset")int offset,@Param("rows")int rows);
    
    int getCustomerListForCount(@Param("mid")long mid);
    
    List<Relation> getCustomerList(@Param("mid")long mid,@Param("offset")int offset,@Param("rows")int rows);
    
    
    
    
    Relation getMerchantByKey(@Param("relaid")long relaid,@Param("mid")long mid);
    
    public int updatePricLevel(@Param("mid")long mid,@Param("relaid")long relaid,@Param("pricLevel")int pricLevel);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);
    
    List<Relation> getRelationListByCreateid(@Param("mid")long mid,@Param("createid")long createid,@Param("classid")long classid,@Param("offset")int offset,@Param("rows")int rows);
    
    int getRelationListByCreateidForCount(@Param("mid")long mid,@Param("createid")long createid,@Param("classid")long classid);
    
    int getRelationListByRelaidForCount(@Param("mid")long mid,@Param("relaid")long relaid);
    
    List<Relation> getRelationListByRelaid(@Param("mid")long mid,@Param("relaid")long relaid);
}