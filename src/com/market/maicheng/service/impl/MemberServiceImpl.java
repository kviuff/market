package com.market.maicheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.MemberDao;
import com.market.maicheng.model.Member;
import com.market.maicheng.service.MemberService;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Autowired 
    private MemberDao memberDao;
    
	@Override
	public int addMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.addMember(member);
	}

	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.updateMember(member);
	}

	@Override
	public Member getMemberForid(long id) {
		// TODO Auto-generated method stub
		return memberDao.getMemberForid(id);
	}
	
	@Override
	public Member getMemberForUserName(String username){
		return memberDao.getMemberForUserName(username);
	}

	@Override
	public Member getMemberForUserNameAndPassword(String username,
			String password) {
		// TODO Auto-generated method stub
		return memberDao.getMemberForUserNameAndPassword(username, password);
	}

	@Override
	public RetInfo getMemberList(long id, String username, int state, int pageNum) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Member> pageVo = new PageVo<Member>(pageNum);
		pageVo.setRows(Constants.PAGESIZE);
		List<Member> list = memberDao.getMemberList(id, username, state, pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getMemberListByCount(long id, String username, int state) {
		return memberDao.getMemberListByCount(id, username, state);
	}
	
	@Override
	public int delMember(long id) {
		return memberDao.delMember(id);
	}

	@Override
	public int updateMemberUserType(long id, int userType) {
		// TODO Auto-generated method stub
		return memberDao.updateMemberUserType(id, userType);
	}

	@Override
	public List<Member> getMemberListByMerchantid(long merchantid) {
		// TODO Auto-generated method stub
		return memberDao.getMemberListByMerchantid(merchantid);
	}

	@Override
	public int updateMemberState(long id, int state) {
		// TODO Auto-generated method stub
		return memberDao.updateMemberState(id, state);
	}

	@Override
	public Member getMemberByWechat(String wechat) {
		return memberDao.getMemberByWechat(wechat);
	}
	
	
}
