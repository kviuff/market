package com.market.maicheng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.News;


public interface NewsService {

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
	public News getNewsById(long _id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delNews(long _id);
	
	/**
	 * 搜索
	 * @param id
	 * @return
	 */
	public List<News> getNewsBySearch(String title);
	/**
	 * 查询总数
	 * @param id
	 * @param istop
	 * @return
	 */
	public int getNewsListByCount(String cateids,int types,String title);
	
	
	/**
	 * 获取分页列表
	 * @param username
	 * @param offset
	 * @param rows
	 * @return
	 */
	public RetInfo getNewsListForPage(String cateids,int types,String username,int pageNum,int pagesize);
	
	/**
	 * 根据类型查询
	 * @param types
	 * @return
	 */
	public List<News> getNewsByTypes(int types,long author);
}
