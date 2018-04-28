package com.market.maicheng.service;

import java.util.List;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.Brand;
import com.market.maicheng.model.Category;

public interface BrandService {
	/**
	 * 添加品牌
	 * @param brand
	 * @return
	 */
	public int addBrand(Brand brand);
	
	/**
	 * 修改品牌
	 * @param brand
	 * @return
	 */
	public int updateBrand(Brand brand);
	
	/**
	 * 删除品牌
	 * @param id
	 * @return
	 */
	public int delBrand(long id);
	
	/**
	 * 获取分页列表
	 * @return
	 */
	public List<Brand> getBrandList();
	
	/**
	 * 根据父id获取品牌列表
	 * @param pid
	 * @return
	 */
	public List<Brand> getBrandListByPid(long pid);
	
	/**
	 * 获取分页列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	public RetInfo getBrandListByPage(String brandName,int pageNum);
	/**
	 * 获取列表数量
	 * @param id
	 * @param username
	 * @return
	 */
	public int getBrandListByCount(String brandName);
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public Brand getBrand(long id);
}
