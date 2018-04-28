package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Category;
import com.market.maicheng.model.MerchantCategory;




@Repository
public interface CategoryDao {
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
	public List<MerchantCategory> getMerchantCategoryById(@Param("mid")long mid);
	
	/**
	 * 根据商家编号删除商家分类列表
	 * @param pid
	 * @return
	 */
	public int delMerchantCategoryById(@Param("mid")long mid);
	
//	/**
//	 * 根据商家编号获取商家分类列表
//	 * @param pid
//	 * @return
//	 */
//	public int getMerchantCategory(@Param("mid")long mid);
	
	/**
	 * 添加
	 * @param mid
	 * @param cid
	 * @return
	 */
	public int addMerchantCategory(@Param("mid")long mid,@Param("cid")long cid);
	
	/**
	 * 获取商家分类列表
	 * @param pid
	 * @return
	 */
	public List<Category> getMerchantCategoryList(@Param("pid")long pid,@Param("istop")int istop,@Param("num")int num);
	
	/**
	 * 获取产品分类列表
	 * @param pid
	 * @return
	 */
	public List<Category> getProductCategoryList(@Param("pid")long pid,@Param("istop")int istop,@Param("num")int num);
	
	
	/**
	 * 根据商家编号获取分类列表
	 * @param mid
	 * @param pid
	 * @return
	 */
	public List<Category> getProductCategoryListLimit(@Param("mid")long mid,@Param("pid")long pid,@Param("istop")int istop,@Param("num")int num);
	
	/**
	 * 根据商家编号获取二级分类列表
	 * @param mid
	 * @param pid
	 * @return
	 */
	public List<Category> getProductSecCategoryListLimit(@Param("mid")long mid,@Param("istop")int istop,@Param("num")int num);
	
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
	public int updateIsTop(@Param("id")long id,@Param("istop")int istop,@Param("updateTime")long updateTime);
	
	/**
	 * 检索所有分类
	 * @param cateName
	 * @return
	 */
	public List<Category> getCategoryBySearchAll(@Param("cateName")String cateName);
	
	/**
	 * 分类接口12
	 * @param pageSize
	 * @return
	 */
	public List<Category> getCateforLimit(@Param("pid")long pid,@Param("pageSize")int pageSize);
}
