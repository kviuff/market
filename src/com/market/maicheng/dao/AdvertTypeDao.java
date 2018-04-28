package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.AdvertType;



@Repository
public interface AdvertTypeDao {
	/**
	 * 添加
	 * @param advert
	 * @return
	 */
	public int addAdvertType(AdvertType advertType);
	
	/**
	 * 修改
	 * @param advert
	 * @return
	 */
	public int upAdvertType(AdvertType advertType);
	
	/**
	 * 获取所有广告类别
	 * @return
	 */
	public List<AdvertType> getAdvertType();
	
	/**
	 * 获取广告实体
	 * @param id
	 * @return
	 */
	public AdvertType getAdvertTypeByID(long id);
	
	/**
	 * 删除广告类别
	 * @return
	 */
	public int delAdvertType(@Param("id")long id);
}
