package com.market.maicheng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.Member;

public interface MemberService {
	/**
	 * 添加用户
	 * @param member
	 * @return
	 */
	public int addMember(Member member);
	
	/**
	 * 修改用户
	 * @param member
	 * @return
	 */
	public int updateMember(Member member);
	
	/**
	 * 通过用户ID获取用户信息
	 * @param id
	 * @return
	 */
	public Member getMemberForid(long id);
	
	/**
	 * 通过用户名获取用户信息
	 * @param username
	 * @return
	 */
	public Member getMemberForUserName(String username);
	
	/**
	 * 通过用户名密码获取用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	public Member getMemberForUserNameAndPassword(String username,String password);
	
	/**
	 * 获取会员列表
	 * @param id
	 * @param username
	 * @return
	 */
	public RetInfo getMemberList(long id,String username,int state,int pageNum);
	
	/**
	 * 获取列表数量
	 * @param id
	 * @param username
	 * @return
	 */
	public int getMemberListByCount(long id,String username,int state);
	
	/**
	 * 删除会员
	 * @param id
	 * @return
	 */
	public int delMember(long id);
	
	/**
	 * 修改用户类型0 普通用户 1 商家用户'
	 * @param id
	 * @return
	 */
	public int updateMemberUserType(long id,int userType);
	
	/**
	 * 根据商家id获取所有员工
	 * @param merchantid
	 * @return
	 */
	public List<Member> getMemberListByMerchantid(long merchantid);
	
	/**
	 * 修改当前状态 0在职 1离职
	 * @param id
	 * @return
	 */
	public int updateMemberState(long id,int state);
}
