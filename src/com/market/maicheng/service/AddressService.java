package com.market.maicheng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.Address;


public interface AddressService {
	/**
	 * 添加地址
	 * @param address
	 * @return
	 */
	public int addAddress(Address address);
	
	/**
	 * 修改用户所有地址默认
	 * @param address
	 * @return
	 */
	public int upAddressdefault(long userid,int isdefault);
	
	/**
	 * 修改地址
	 * @param address
	 * @return
	 */
	public int upAddress(Address address);
	
	/**
	 * 获取用户快递列表
	 * @param userid
	 * @return
	 */
	public RetInfo getAddressListPage(long userid,int pageNum,int pageSize);
	/**
	 * 个数
	 * @param userid
	 * @return
	 */
	public int getAddressListCount(long userid);
	
	/**
	 * 删除快递地址
	 * @param id
	 * @return
	 */
	public int delAddress(long id);
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public Address getAddress(long id);
	
	/**
	 * 地址列表
	 * @param userid
	 * @return
	 */
	public List<Address> getAddressList(long userid);
}
