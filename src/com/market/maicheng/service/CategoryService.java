package com.market.maicheng.service;

import java.util.List;

import com.market.maicheng.model.Category;
import com.market.maicheng.model.MerchantCategory;



public interface CategoryService {
	/**
	 * 添加
	 * @param category
	 * @return
	 */
	public int addCate(Category category);
	
	/**
	 * 修改
	 * @param category
	 * @return
	 */
	public int updateCate(Category category);
	/**
	 * 根据商家编号获取商家分类列表
	 * @param pid
	 * @return
	 */
	public List<MerchantCategory> getMerchantCategoryById(long mid);
	
	/**
	 * 获取商家分类列表
	 * @param pid
	 * @return
	 */
	public List<Category> getMerchantCategoryList(long pid,int istop,int num);
	
	/**
	 * 获取商品分类列表
	 * @param pid
	 * @return
	 */
	public List<Category> getProductCategoryList(long pid,int istop,int num);
	
	
	/**
	 * 根据商家编号获取分类列表
	 * @param pid
	 * @return
	 */
	public List<Category> getProductCategoryListLimit(long mid,long pid,int istop,int num);
	/**
	 * 根据商家编号获取二级分类列表
	 * @param pid
	 * @return
	 */
	public List<Category> getProductSecCategoryListLimit(long mid,int istop,int num);
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public Category getCategory(long id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delCate(long id);
	
	/**
	 * 修改首推
	 * @param id
	 * @param istop
	 * @return
	 */
	public int updateIsTop(long id,int istop);
	
	/**
	 * 检索所有分类
	 * @param cateName
	 * @return
	 */
	public List<Category> getCategoryBySearchAll(String cateName);
	
	/**
	 * 分类接口12
	 * @param pageSize
	 * @return
	 */
	public List<Category> getCateforLimit(long pid,int pageSize);
}

