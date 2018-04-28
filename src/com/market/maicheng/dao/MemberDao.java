package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Member;

@Repository
public interface MemberDao {
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
	 * 根据商家id获取所有员工
	 * @param merchantid
	 * @return
	 */
	public List<Member> getMemberListByMerchantid(@Param("merchantid")long merchantid);
	
	/**
	 * 通过用户名获取用户信息
	 * @param username
	 * @return
	 */
	public Member getMemberForUserName(@Param("username")String username);
	
	/**
	 * 通过用户名密码获取用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	public Member getMemberForUserNameAndPassword(@Param("username")String username,@Param("password")String password);
	
	/**
	 * 获取会员列表
	 * @param id
	 * @param username
	 * @return
	 */
	public List<Member> getMemberList(@Param("id")long id,@Param("username")String username,@Param("state")int state,@Param("offset") long offset, @Param("rows") long rows);
	
	/**
	 * 获取列表数量
	 * @param id
	 * @param username
	 * @return
	 */
	public int getMemberListByCount(@Param("id")long id,@Param("username")String username,@Param("state")int state);

	/**
	 * 删除会员
	 * @param id
	 * @return
	 */
	public int delMember(@Param("id")long id);
	
	/**
	 * 修改用户类型0 普通用户 1 商家用户'
	 * @param id
	 * @return
	 */
	public int updateMemberUserType(@Param("id")long id,@Param("userType")int userType);
	
	
	/**
	 * 修改当前状态 0在职 1离职
	 * @param id
	 * @return
	 */
	public int updateMemberState(@Param("id")long id,@Param("state")int state);
}
