package com.market.maicheng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.model.Product;
import com.market.maicheng.model.SameGood;

public interface ProductService {
	/**
	 * 添加产品
	 * @param product
	 * @return
	 */
	public int addProduct(Product product);
	
	/**
	 * 修改产品
	 * @param product
	 * @return
	 */
	public int updateProduct(Product product);
	
	/**
	 * 产品至顶
	 * @param pid
	 * @param istop
	 * @return
	 */
	public int updateProductTop(long pid,int istop);
	
	/**
	 * 统计分类下的产品数
	 * @param product
	 * @return
	 */
	public void statisticsProduct(Product product);
	
	/**
	 * 删除产品--非物理删除
	 * @param id
	 * @return
	 */
	public int delProduct(long id);
	
	/**
	 * 获取产品实体
	 * @param id
	 * @return
	 */
	public Product getProductByID(long id);
	
	/**
	 * 根据条形码查询所有产品
	 * @param barcodeid
	 * @return
	 */
	public Product getProductByBarcodeID(String barcodeid);
	
	/**
	 * 根据商户ID查询所有产品
	 * @param merchantID
	 * @return
	 */
	public List<Product> getProductByMerchantID(long merchantID);
	
	/**
	 * 获取商铺产品分页
	 * @param pageNum
	 * @param state
	 * @param pagesize
	 * @return
	 */
	public RetInfo getProductsForPage(int pageNum,long merchantID,long categoryID,String barcodeid,String title,String orderby,int pagesize);
	
	/**
	 * 获取商铺产品列表总数
	 * @param id
	 * @param username
	 * @return
	 */
	public int getProductsForPageByCount(long merchantID,long categoryID,String barcodeid,String title,String orderby);
	
	
	/**
	 * 获取商铺产品列表总数
	 * @param id
	 * @param username
	 * @return
	 */
	public int getProductsSgByCount(long merchantID,long categoryID,String barcodeid,String title,String orderby);
	/**
	 * 查询商铺产品分页
	 * @param pageNum
	 * @param state
	 * @param pagesize
	 * @return
	 */
	public RetInfo getProductsHasSgForPage(int pageNum,long merchantID,long categoryID,String barcodeid,String title,String orderby,int pagesize);
	
	/**
	 * 查询商铺产品(不分页,不带同品)
	 * @param merchantID
	 * @param categoryID
	 * @param barcodeid
	 * @param title
	 * @param orderby
	 * @return
	 */
	public List<Product> getProducts(long merchantID,long categoryID,String barcodeid,String title,String orderby);
	
	/**
	 * 查询商铺产品(不分页,带同品)
	 * @param merchantID
	 * @param categoryID
	 * @param barcodeid
	 * @param title
	 * @param orderby
	 * @return
	 */
	public List<Product> getProductsHasSg(long merchantID,long categoryID,String barcodeid,String title,String orderby);
	
	/**
	 * 添加同品关系
	 * @param sameGoods
	 * @return
	 */
	public int addSameGood(SameGood sameGood);
	
	/**
	 * 设置同品
	 * @param sid
	 * @param mid
	 * @param barcodeId
	 * @return
	 */
	public int setSameGoods(String sid,long mid,String barcodeId);
	
	/**
	 * 获取商铺产品列表总数
	 * @param id
	 * @param username
	 * @return
	 */
	public List<Product> getSameGoods(long merchantID,long categoryID,String barcodeid,String title);
	
	/**
	 * 获取商户同品
	 * @param mid
	 * @param barcodeid
	 * @return
	 */
	public SameGood getSameGood(long mid,String barcodeid);
	/**
	 * 删除同品
	 * @param barcodeId
	 * @param sid
	 * @return
	 */
	public int deleteSameGoods(String barcodeId,String sid);
	
}
