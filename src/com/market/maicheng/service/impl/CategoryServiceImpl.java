package com.market.maicheng.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.dao.CategoryDao;
import com.market.maicheng.model.Category;
import com.market.maicheng.model.MerchantCategory;
import com.market.maicheng.service.CategoryService;


@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public int addCate(Category category) {
		return categoryDao.addCate(category);
	}

	@Override
	public int updateCate(Category category) {
		category.setUpdateTime((new Date()).getTime());
		return categoryDao.updateCate(category);
	}
	
	@Override
	public List<Category> getMerchantCategoryList(long pid,int istop,int num) {
		return categoryDao.getMerchantCategoryList(pid,istop,num);
	}

	@Override
	public List<Category> getProductCategoryList(long pid,int istop,int num) {
		return categoryDao.getProductCategoryList(pid,istop,num);
	}

	@Override
	public int delCate(long id) {
		return categoryDao.delCate(id);
	}
	
	public Category getCategory(long id){
		return categoryDao.getCategory(id);
	}

	@Override
	public int updateIsTop(long id, int istop) {
		return categoryDao.updateIsTop(id, istop,(new Date()).getTime());
	}

	@Override
	public List<Category> getCategoryBySearchAll(String cateName) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryBySearchAll(cateName);
	}

	@Override
	public List<Category> getCateforLimit(long pid,int pageSize) {
		// TODO Auto-generated method stub
		return categoryDao.getCateforLimit(pid,pageSize);
	}

	@Override
	public List<Category> getProductCategoryListLimit(long mid, long pid, int istop, int num) {
		// TODO Auto-generated method stub
		return categoryDao.getProductCategoryListLimit(mid, pid, istop, num);
	}

	@Override
	public List<Category> getProductSecCategoryListLimit(long mid, int istop, int num) {
		// TODO Auto-generated method stub
		return categoryDao.getProductSecCategoryListLimit(mid, istop, num);
	}

	@Override
	public List<MerchantCategory> getMerchantCategoryById(long mid) {
		// TODO Auto-generated method stub
		return categoryDao.getMerchantCategoryById(mid);
	}

}
