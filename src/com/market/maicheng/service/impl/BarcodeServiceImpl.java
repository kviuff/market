package com.market.maicheng.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.BarcodeDao;
import com.market.maicheng.model.Barcode;
import com.market.maicheng.service.BarcodeService;

@Service("BarcodeService")
public class BarcodeServiceImpl implements BarcodeService {
	
	@Autowired
	BarcodeDao barcodeDao;

	@Override
	public int addBarcode(Barcode barcode) {
		// TODO Auto-generated method stub
		return barcodeDao.addBarcode(barcode);
	}

	@Override
	public int updateBarcode(Barcode barcode) {
		// TODO Auto-generated method stub
		return barcodeDao.updateBarcode(barcode);
	}

	@Override
	public int delBarcode(long id) {
		// TODO Auto-generated method stub
		return barcodeDao.delBarcode(id);
	}

	@Override
	public Barcode getBarcodeForID(long id) {
		// TODO Auto-generated method stub
		return barcodeDao.getBarcodeForID(id);
	}

	@Override
	public Barcode getBarcodeForBarcodeid(String barcodeid) {
		// TODO Auto-generated method stub
		return barcodeDao.getBarcodeForBarcodeid(barcodeid);
	}

	@Override
	public RetInfo getBarcodeList(String brandName,int pageNum) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Barcode> pageVo = new PageVo<Barcode>(pageNum);
		pageVo.setRows(Constants.PAGESIZE);
		List<Barcode> list = barcodeDao.getBarcodeList(brandName,pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getBarcodeListByCount(String brandName) {
		// TODO Auto-generated method stub
		return barcodeDao.getBarcodeListByCount(brandName);
	}

}
