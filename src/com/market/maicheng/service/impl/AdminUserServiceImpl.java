package com.market.maicheng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.AdminUserDao;
import com.market.maicheng.model.AdminUser;
import com.market.maicheng.service.AdminUserService;

@Service("AdminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;
	
	@Override
	public AdminUser getAdminUserByID(long id) {
		return adminUserDao.getAdminUserByID(id);
	}

	@Override
	public AdminUser getAdminUserByUserPass(String username, String password) {
		return adminUserDao.getAdminUserByUserPass(username, password);
	}

	@Override
	public int addAdminUser(AdminUser adminUser) {
		return adminUserDao.addAdminUser(adminUser);
	}

	@Override
	public int deleteAdminUser(long userid) {
		return adminUserDao.deleteAdminUser(userid);
	}

	@Override
	public int updateAdminUser(AdminUser adminUser) {
		return adminUserDao.updateAdminUser(adminUser);
	}

	@Override
	public RetInfo getAdminUserListForPage(int pageNum, String username) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		info.setRetMsg("查询管理员失败");
		PageVo<AdminUser> pageVo = new PageVo<AdminUser>(pageNum);
		pageVo.setRows(Constants.PAGESIZE);
		List<AdminUser> list = new ArrayList<AdminUser>();
		list = adminUserDao.getAdminUserListForPage(username, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		info.setRetMsg("查询管理员成功");
		return info;
	}

	@Override
	public int getAdminUserListByCount(String username) {
		return adminUserDao.getAdminUserListByCount(username);
	}

	@Override
	public AdminUser getAdminUserByUserName(String username) {
		return adminUserDao.getAdminUserByUserName(username);
	}

	@Override
	public int unlockAdminUser(long id, int unlock) {
		return adminUserDao.unlockAdminUser(id, unlock);
	}

}
