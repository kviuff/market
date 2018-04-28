package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.AddressDao;
import com.market.maicheng.model.Address;
import com.market.maicheng.service.AddressService;


@Service("AddressService")
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public int addAddress(Address address) {
		return addressDao.addAddress(address);
	}

	@Override
	public int upAddress(Address address) {
		return addressDao.upAddress(address);
	}

	@Override
	public RetInfo getAddressListPage(long userid, int pageNum, int pageSize) {
		RetInfo info = new RetInfo();
		PageVo<Address> pageVo = new PageVo<Address>(pageNum);
		pageVo.setRows(pageSize);
		List<Address> list = addressDao.getAddressListPage(userid, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		return info;
	}

	@Override
	public int delAddress(long id) {
		return addressDao.delAddress(id);
	}

	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public Address getAddress(long id){
		return addressDao.getAddress(id);
	}

	@Override
	public int getAddressListCount(long userid) {
		// TODO Auto-generated method stub
		return addressDao.getAddressListCount(userid);
	}

	@Override
	public List<Address> getAddressList(long userid) {
		// TODO Auto-generated method stub
		return addressDao.getAddressList(userid);
	}

	@Override
	public int upAddressdefault(long userid, int isdefault) {
		// TODO Auto-generated method stub
		return addressDao.upAddressdefault(userid,isdefault);
	}
}
