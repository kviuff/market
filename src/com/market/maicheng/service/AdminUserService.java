package com.market.maicheng.service;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.AdminUser;

public interface AdminUserService {
	/**
	 * 根據用戶id獲取用戶實體
	 * @param id
	 * @return
	 */
	public AdminUser getAdminUserByID(long id);
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUser getAdminUserByUserPass(String username,String password);
	
	/**
	 * 添加用户
	 * @param adminUser
	 * @return
	 */
	public int addAdminUser(AdminUser adminUser);
	
	/**
	 * 删除用户
	 * @param adminUser
	 * @return
	 */
	public int deleteAdminUser(long userid);
	
	/**
	 * 修改用户
	 * @param adminUser
	 * @return
	 */
	public int updateAdminUser(AdminUser adminUser);
	
	/**
	 * 获取分页列表
	 * @param pageNum
	 * @param username
	 * @return
	 */
	public RetInfo getAdminUserListForPage(int pageNum, String username);
	
	/**
	 *  获取用户列表总数
	 * @param username
	 * @return
	 */
	public int getAdminUserListByCount(String username);
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public AdminUser getAdminUserByUserName(String username);
	
	/**
	 * 用户锁定状态
	 * @param id
	 * @param unlock
	 * @return
	 */
	public int unlockAdminUser(long id,int unlock);
}
