package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.dao.BarcodePriceDao;
import com.market.maicheng.model.BarcodePrice;
import com.market.maicheng.service.BarcodePriceService;

@Service("BarcodePriceService")
public class BarcodePriceServiceImpl implements BarcodePriceService {
	@Autowired
	private BarcodePriceDao barcodePriceDao;

	@Override
	public int addBarcodePrice(BarcodePrice barcodePrice) {
		return barcodePriceDao.addBarcodePrice(barcodePrice);
	}

	@Override
	public int updateBarcodePrice(BarcodePrice barcodePrice) {
		return barcodePriceDao.updateBarcodePrice(barcodePrice);
	}

	@Override
	public int delBarcodePrice(long id) {
		return barcodePriceDao.delBarcodePrice(id);
	}

	@Override
	public List<BarcodePrice> getBarcodePriceByBarcodeID(long barcodeid) {
		return barcodePriceDao.getBarcodePriceByBarcodeID(barcodeid);
	}

	@Override
	public BarcodePrice getBarcodePriceByid(long id) {
		// TODO Auto-generated method stub
		return barcodePriceDao.getBarcodePriceByid(id);
	}

}
