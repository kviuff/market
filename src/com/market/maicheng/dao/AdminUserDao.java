package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.AdminUser;

@Repository
public interface AdminUserDao {
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
	public AdminUser getAdminUserByUserPass(@Param("username")String username,@Param("password")String password);
	
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
	public int deleteAdminUser(long id);
	
	/**
	 * 修改用户
	 * @param adminUser
	 * @return
	 */
	public int updateAdminUser(AdminUser adminUser);
	
	/**
	 * 获取分页列表
	 * @param username
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<AdminUser> getAdminUserListForPage(@Param("username") String username ,@Param("offset") long offset, @Param("rows") long rows);
	
	/**
	 * 获取用户列表总数
	 * @return
	 */
	public int getAdminUserListByCount(@Param("username") String username);
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public AdminUser getAdminUserByUserName(@Param("username") String username);
	
	/**
	 * 用户锁定状态
	 * @param id
	 * @param unlock
	 * @return
	 */
	public int unlockAdminUser(@Param("id") long id,@Param("unlock") int unlock);
}
