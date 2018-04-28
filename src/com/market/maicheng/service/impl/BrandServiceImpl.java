package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.BrandDao;
import com.market.maicheng.model.Brand;
import com.market.maicheng.service.BrandService;

@Service("BrandService")
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandDao brandDao;
	
	@Override
	public int addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandDao.addBrand(brand);
	}

	@Override
	public int updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandDao.updateBrand(brand);
	}

	@Override
	public int delBrand(long id) {
		// TODO Auto-generated method stub
		return brandDao.delBrand(id);
	}

	@Override
	public RetInfo getBrandListByPage(String brandName,int pageNum) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Brand> pageVo = new PageVo<Brand>(pageNum);
		pageVo.setRows(Constants.PAGESIZE);
		List<Brand> list = brandDao.getBrandListByPage(brandName,pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getBrandListByCount(String brandName) {
		return brandDao.getBrandListByCount(brandName);
	}

	@Override
	public List<Brand> getBrandList() {
		// TODO Auto-generated method stub
		return brandDao.getBrandList();
	}

	@Override
	public List<Brand> getBrandListByPid(long pid) {
		// TODO Auto-generated method stub
		return brandDao.getBrandListByPid(pid);
	}

	@Override
	public Brand getBrand(long id) {
		return brandDao.getBrand(id);
	}

}
