package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Favorite;

/**
 * 功能:收藏店铺
 * 创建时间: 2017/12/20.14:50
 *
 * @author 高红飞(gaohongfeipc)
 * @version(版本)：TODO
 * @since JDK 7
 */
@Repository
public interface FavoriteDao {
	/**
	 * 获取一条收藏
	 * @param relaid
	 * @param mid
	 * @return
	 */
	Favorite getFavoriteByKey(@Param("createid")long relaid,@Param("mid")long mid);
	
	
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
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Favorite> getList(@Param("createid")long createid,@Param("classid")long classid,@Param("offset")int offset,@Param("rows")int rows);
    /**
     * 获取分页总条数
     * @param createid
     * @param classid
     * @return
     */
    int getListForCount(@Param("createid")long createid,@Param("classid")long classid);
    /**
     * 根据主键删除一条收藏
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
}
