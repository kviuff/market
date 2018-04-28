package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.market.maicheng.model.Merchant;

@Repository
public interface MerchantDao {
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
	public List<Merchant> getMerchantList(@Param("state")int state,@Param("shopName")String shopName,@Param("categoryID")long categoryID,@Param("provinceId")long provinceId,@Param("cityId")long cityId,@Param("regionid")long regionid,@Param("offset") long offset, @Param("rows") long rows);
	
	
	/**
	 * 获取商户申请列表数量
	 * @param id
	 * @param username
	 * @return
	 */
	public int getMerchantByCount(@Param("state")int state,@Param("shopName")String shopName,@Param("categoryID")long categoryID,@Param("provinceId")long provinceId,@Param("cityId")long cityId,@Param("regionid")long regionid);
	
	/**
	 * 获取商户信息
	 * @param userid
	 * @return
	 */
	public Merchant getMerchantByUserid(long userid);
	
	/**
	 * 修改商户审核状态
	 * @param state
	 * @return
	 */
	public int changeMerchantState(@Param("id")long id,@Param("state")int state);
	
	/**
	 * 修改商户推荐状态
	 * @param state
	 * @return
	 */
	public int changeMerchantRecommend(@Param("id")long id,@Param("updateTime")long updateTime,@Param("recommend")int recommend);
	
	/**
	 * 获取指定数量推荐商户
	 * @param limie
	 * @return
	 */
	public List<Merchant> getMerchantRecommendList(@Param("limit")int limit);
	
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
	public int updateMerchantPubPric(@Param("id")long id,@Param("publicPric")int publicPric);
	
	/**
	 * 根据id获取商户信息
	 * @param mid
	 * @return
	 */
	public Merchant getMerchantByid(@Param("id")long id);
}
