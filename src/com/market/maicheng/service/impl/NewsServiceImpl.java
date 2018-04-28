package com.market.maicheng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.NewsDao;
import com.market.maicheng.model.News;
import com.market.maicheng.service.NewsService;


@Service("NewsService")
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDao newsdao;

	@Override
	public int addNews(News news) {
		// TODO Auto-generated method stub
		return newsdao.addNews(news);
	}

	@Override
	public int updateNews(News news) {
		// TODO Auto-generated method stub
		return newsdao.updateNews(news);
	}

	@Override
	public List<News> getNewsList() {
		// TODO Auto-generated method stub
		return newsdao.getNewsList();
	}

	@Override
	public News getNewsById(long _id) {
		// TODO Auto-generated method stub
		return newsdao.getNewsById(_id);
	}

	@Override
	public int delNews(long _id) {
		// TODO Auto-generated method stub
		return newsdao.delNews(_id);
	}

	@Override
	public List<News> getNewsBySearch(String title) {
		// TODO Auto-generated method stub
		return newsdao.getNewsBySearch(title);
	}

	@Override
	public int getNewsListByCount(String cateids,int types,String title) {
		// TODO Auto-generated method stub
		return newsdao.getNewsListByCount(types,title);
	}

	public RetInfo getNewsListForPage(String cateids,int types,String username,int pageNum,int pagesize) {
		RetInfo info = new RetInfo();
		PageVo<News> pageVo = new PageVo<News>(pageNum);
		pageVo.setRows(pagesize);
		List<News> list = new ArrayList<News>();
		list = newsdao.getNewsListForPage(types,username, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		return info;
	}

	@Override
	public List<News> getNewsByTypes(int types,long author) {
		// TODO Auto-generated method stub
		return newsdao.getNewsByTypes(types,author);
	}


}
