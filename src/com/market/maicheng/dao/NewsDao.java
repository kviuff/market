package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.News;


@Repository
public interface NewsDao {

	/**
	 * 添加
	 * @param news
	 * @return
	 */
	public int addNews(News news);
	
	/**
	 * 修改
	 * @param news
	 * @return
	 */
	public int updateNews(News news);
	
	/**
	 * 获取列表
	 * @param 
	 * @return
	 */
	public List<News> getNewsList();
	
	/**
	 * 根据id获取
	 * @param pid
	 * @return
	 */
	public News getNewsById(long id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delNews(long id);
	
	/**
	 * 搜索
	 * @param id
	 * @return
	 */
	public List<News> getNewsBySearch(@Param("title")String title);
	/**
	 * 查询总数
	 * @param id
	 * @param istop
	 * @return
	 */
	public int getNewsListByCount(@Param("types")int types,@Param("title")String title);
	
	
	/**
	 * 获取分页列表
	 * @param username
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<News> getNewsListForPage(@Param("types")int types,@Param("title") String title ,@Param("offset") long offset, @Param("rows") long rows);
	
	/**
	 * 根据类型查询
	 * @param types
	 * @return
	 */
	public List<News> getNewsByTypes(@Param("types") int types,@Param("author")long author);
}
