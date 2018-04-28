package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Address;


@Repository
public interface AddressDao {
	
	/**
	 * 添加地址
	 * @param address
	 * @return
	 */
	public int addAddress(Address address);
	/**
	 * 修改地址
	 * @param address
	 * @return
	 */
	public int upAddress(Address address);
	
	/**
	 * 修改用户所有地址默认
	 * @param address
	 * @return
	 */
	public int upAddressdefault(@Param("userid")long userid, @Param("isdefault")int isdefault);
	
	/**
	 * 获取用户快递列表
	 * @param userid
	 * @return
	 */
	public List<Address> getAddressListPage(@Param("userid")long userid,@Param("offset") long offset, @Param("rows") long rows);
	
	/**
	 * 获取列表数量
	 * @param id
	 * @param username
	 * @return
	 */
	public int getAddressListCount(@Param("userid")long userid);
	
	/**
	 * 删除快递地址
	 * @param id
	 * @return
	 */
	public int delAddress(@Param("id")long id);
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	public Address getAddress(@Param("id")long id);
	/**
	 * 地址列表
	 * @param userid
	 * @return
	 */
	public List<Address> getAddressList(@Param("userid")long userid);
}
