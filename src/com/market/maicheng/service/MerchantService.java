package com.market.maicheng.service;


import java.util.List;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.Merchant;

public interface MerchantService {
	/**
	 * 添加商户申请
	 * @param merchant
	 * @return
	 */
	public int addMerchant(Merchant merchant);
	
	/**
	 * 修改商户申请
	 * @param merchant
	 * @return
	 */
	public int updateMerchant(Merchant merchant);
	
	/**
	 * 获取商户申请列表
	 * @param id
	 * @param username
	 * @return
	 */
	public RetInfo getMerchantList(int pageNum,int state,String shopName,long categoryID,long provinceId,long cityId,long regionid,int pagesize);
	
	/**
	 * 获取商户申请列表总数
	 * @param id
	 * @param username
	 * @return
	 */
	public int getMerchantByCount(int state,String shopName,long categoryID,long provinceId,long cityId,long regionid);
	
	/**
	 * 根据用户id获取商户信息
	 * @param userid
	 * @return
	 */
	public Merchant getMerchantByUserid(long userid);
	
	/**
	 * 根据id获取商户信息
	 * @param mid
	 * @return
	 */
	public Merchant getMerchantByid(long id);
	
	/**
	 * 更新商户分类
	 * @param id
	 * @param cIds
	 * @return
	 */
	public int updateMerchantCategory(long id,List<Long> cIds);
	
	/**
	 * 修改商户审核状态
	 * @param state
	 * @return
	 */
	public int changeMerchantState(long id,int state);
	
	/**
	 * 修改商户推荐状态
	 * @param state
	 * @return
	 */
	public int changeMerchantRecommend(long id,long updateTime,int recommend);
	
	/**
	 * 获取指定数量推荐商户
	 * @param limie
	 * @return
	 */
	public List<Merchant> getMerchantRecommendList(int limit);
	/**
	 * 更新商户信息
	 * @param merchant
	 * @return
	 */
	public int updateMerchantInformation(Merchant merchant);
	
	/**
	 * 设置商户价格是否公开
	 * @param id
	 * @param publicPric
	 * @return
	 */
	public int updateMerchantPubPric(long id,int publicPric);
}
