package com.market.maicheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cache.decorators.LruCache;

import com.market.maicheng.model.Product;
import com.market.maicheng.model.SameGood;

/**
 * 产品表dao
 * @author Administrator
 *
 */
public interface ProductDao {
	
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
	public int updateProductTop(@Param("pid")long pid,@Param("istop")int istop);
	
	/**
	 * 统计分类下的产品数
	 * @param product
	 * @return
	 */
	public void statisticsProduct(Product product);
	
//	/**
//	 * 获取统计分类
//	 * @param product
//	 * @return
//	 */
//	public StatisticsCategoryProduct  getCategoryMerchantRelation(Product product);
	
	/**
	 * 删除产品--非物理删除
	 * @param id
	 * @return
	 */
	public int delProduct(@Param("id")long id);
	
	/**
	 * 获取产品实体
	 * @param id
	 * @return
	 */
	public Product getProductByID(@Param("id")long id);
	
	/**
	 * 根据条形码查询所有产品
	 * @param barcodeid
	 * @return
	 */
	public Product getProductByBarcodeID(@Param("barcodeId")String barcodeId);
	

	
	/**
	 * 根据商户ID查询所有产品
	 * @param merchantID
	 * @return
	 */
	public List<Product> getProductByMerchantID(@Param("merchantID")long merchantID);
	
	/**
	 * 获取商铺产品分页（不带同品）
	 * @param merchantID
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<Product> getProductsForPage(@Param("merchantID")long merchantID,@Param("categoryID")long categoryID,@Param("barcodeId")String barcodeId,@Param("title")String title,@Param("orderby")String orderby,@Param("offset") long offset, @Param("rows") long rows);

	
	/**
	 * 获取商户申请列表数量（不带同品）
	 * @param merchantID
	 * @param categoryID
	 * @param barcodeId
	 * @param title
	 * @param orderby
	 * @return
	 */
	public int getProductsByCount(@Param("merchantID")long merchantID,@Param("categoryID")long categoryID,@Param("barcodeId")String barcodeId,@Param("title")String title,@Param("orderby")String orderby);

	/**
	 * 获取商户申请列表数量
	 * @param merchantID
	 * @param categoryID
	 * @param barcodeId
	 * @param title
	 * @param orderby
	 * @return
	 */
	public int getProductsSgByCount(@Param("merchantID")long merchantID,@Param("categoryID")long categoryID,@Param("barcodeId")String barcodeId,@Param("title")String title,@Param("orderby")String orderby);

	/**
	 * 获取商铺产品分页
	 * @param merchantID
	 * @param categoryID
	 * @param barcodeId
	 * @param title
	 * @param orderby
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<Product> getProductsHasSgForPage(@Param("merchantID")long merchantID,@Param("categoryID")long categoryID,@Param("barcodeId")String barcodeId,@Param("title")String title,@Param("orderby")String orderby,@Param("offset") long offset, @Param("rows") long rows);

	/**
	 * 获取商铺产品不分页(不带同品)
	 * @param merchantID
	 * @param categoryID
	 * @param title
	 * @param orderby
	 * @return
	 */
	public List<Product> getProductsHasSg(@Param("merchantID")long merchantID,@Param("categoryID")long categoryID,@Param("barcodeId")String barcodeId,@Param("title")String title,@Param("orderby")String orderby);

	
	/**
	 * 获取商铺产品不分页(带同品)
	 * @param merchantID
	 * @param categoryID
	 * @param title
	 * @param orderby
	 * @return
	 */
	public List<Product> getProducts(@Param("merchantID")long merchantID,@Param("categoryID")long categoryID,@Param("barcodeId")String barcodeId,@Param("title")String title,@Param("orderby")String orderby);

	

	/**
	 * 获取商户同品
	 * @param merchantID
	 * @param categoryID
	 * @param barcodeid
	 * @param title
	 * @return
	 */
	public List<Product> getSameGoods(@Param("merchantID")long merchantID,@Param("categoryID")long categoryID,@Param("barcodeId")String barcodeId,@Param("title")String title);
	/**
	 * 添加同品关系
	 * @param sameGoods
	 * @return
	 */
	public int addSameGood(SameGood sameGood);
	
	/**
	 * 根据条形码查询同品编码
	 * @param barcodeId
	 * @return
	 */
	public List<SameGood> getSameGoodsByBarcodeID(@Param("barcodeId")String barcodeId);
	
	/**
	 * 删除同品关系
	 * @param barcodeId
	 * @param sid
	 * @return
	 */
	public int delSameGood(@Param("barcodeId")String barcodeId,@Param("sid")String sid);
	/**
	 * 获取商户同品
	 * @param mid
	 * @param barcodeid
	 * @return
	 */
	public SameGood getSameGood(@Param("mid")long mid,@Param("barcodeId")String barcodeId);

	/**
	 * 删除同品
	 * @param sid
	 * @param barcodeId
	 * @return
	 */
	public int deleteSameGoods(@Param("barcodeId")String barcodeId,@Param("sid")String sid);
}

