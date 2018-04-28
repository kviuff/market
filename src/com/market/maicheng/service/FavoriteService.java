package com.market.maicheng.service;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.Favorite;

/**
 * 功能:用户收藏店铺
 * 创建时间: 2017/12/20.16:06
 *
 * @author 高红飞(gaohongfeipc)
 * @version(版本)：TODO
 * @since JDK 7
 */
public interface FavoriteService {
	/**
	 * 根据主键判断记录是否存在
	 * @param createid
	 * @param mid
	 * @return
	 */
	public boolean isExist(long createid,long mid);
	
	/**
	 * 添加收藏
	 * @param record
	 * @return
	 */
	public int insert(Favorite record);
	
	/**
	 * 获取分页收藏列表
	 * @param createid
	 * @param classid
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	public RetInfo getList(long createid,long classid,int pageNum,int pagesize);
	
	/**
	 * 获取分页总条数
	 * @param createid
	 * @param classid
	 * @return
	 */
	public int getListForCount(long createid,long classid);
	
	/**
	 * 软删除一条收藏
	 * @param id
	 * @return
	 */
	public int delete(long id);

}
