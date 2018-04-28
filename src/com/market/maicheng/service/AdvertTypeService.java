package com.market.maicheng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.maicheng.model.AdvertType;

public interface AdvertTypeService {
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
	 * 获取广告类别实体
	 * @param id
	 * @return
	 */
	public AdvertType getAdvertTypeByID(long id);
	/**
	 * 删除广告类别
	 * @return
	 */
	public int delAdvertType(long id);
}
