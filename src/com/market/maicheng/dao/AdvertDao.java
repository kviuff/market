package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Advert;




@Repository
public interface AdvertDao {
	/**
	 * 添加
	 * @param advert
	 * @return
	 */
	public int addAdvert(Advert advert);
	
	/**
	 * 修改
	 * @param advert
	 * @return
	 */
	public int upAdvert(Advert advert);
	
	/**
	 * 获取所有广告
	 * @return
	 */
	public List<Advert> getAdvert();
	
	/**
	 * 获取类别广告
	 * @param type
	 * @return
	 */
	public List<Advert> getAdvertForType(@Param("type")long type);
	
	/**
	 * 获取广告
	 * @param id
	 * @return
	 */
	public Advert getAdvertForID(long id);
	
	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	public int delAdvertForID(long id); 
}
