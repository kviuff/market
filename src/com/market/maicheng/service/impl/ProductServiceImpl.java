package com.market.maicheng.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.maicheng.common.utils.Constants;
import com.market.maicheng.common.utils.MybatisUtils;
import com.market.maicheng.common.utils.PageVo;
import com.market.maicheng.common.utils.RetInfo;
import com.market.maicheng.dao.ProductDao;
import com.market.maicheng.model.Product;
import com.market.maicheng.model.SameGood;
import com.market.maicheng.service.ProductService;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public int addProduct(Product product) {
		int result = productDao.addProduct(product);
		//this.statisticsProduct(product);
		return result;
	}

	@Override
	public int updateProduct(Product product) {
		int result = productDao.updateProduct(product);
		//this.statisticsProduct(product);
		return result;
	}

	@Override
	public void statisticsProduct(Product product) {
		productDao.statisticsProduct(product);
	}
	
	@Override
	public int delProduct(long id) {
		int result = productDao.delProduct(id);
		Product product = this.getProductByID(id);
		//this.statisticsProduct(product);
		return result;
	}

	@Override
	public Product getProductByID(long id) {
		return productDao.getProductByID(id);
	}

	@Override
	public Product getProductByBarcodeID(String barcodeid) {
		return productDao.getProductByBarcodeID(barcodeid);
	}
	
	@Override
	public List<Product> getProductByMerchantID(long merchantID) {
		return productDao.getProductByMerchantID(merchantID);
	}

	@Override
	public RetInfo getProductsForPage(int pageNum, long merchantID,long categoryID,String barcodeid,String title,String orderby,
			int pagesize) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Product> pageVo = new PageVo<Product>(pageNum);
		pageVo.setRows(pagesize);
		List<Product> list = productDao.getProductsForPage(merchantID,categoryID,barcodeid,title,orderby,pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getProductsForPageByCount(long merchantID,long categoryID,String barcodeid,String title,String orderby) {
		// TODO Auto-generated method stub
		return productDao.getProductsByCount(merchantID,categoryID,barcodeid,title,orderby);
	}

//	@Override
//	public int setSameGoods(String barcodeId, String barcodeId2, int isAdd) {
//		int result = 0;
//		List<SameGood> sameGoods = productDao.getSameGoodsByBarcodeID(barcodeId);
//		String sid = "";
//		if(sameGoods==null||sameGoods.size()==0){//如果不存在，添加
//			sid = java.util.UUID.randomUUID().toString().replaceAll("-", "");
//			SameGood sameGood = new SameGood();
//			sameGood.setSid(sid);
//			sameGood.setBarcodeId(barcodeId);
//			result = productDao.addSameGood(sameGood);
//		}else{
//			sid = sameGoods.get(0).getSid();
//		}
//		if(isAdd==1){
//			SameGood sameGood = new SameGood();
//			sameGood.setSid(sid);
//			sameGood.setBarcodeId(barcodeId2);
//			result = productDao.addSameGood(sameGood);
//		}else{
//			result = productDao.delSameGood(barcodeId2, sid);
//		}
//		
//		// TODO Auto-generated method stub
//		return result;
//	}
	
	@Override
	public int setSameGoods(String sid,long mid,String barcodeId) {
		int result = 0;

			SameGood sameGood = new SameGood();
			sameGood.setSid(sid);
			sameGood.setMid(mid);
			sameGood.setBarcodeId(barcodeId);
			result = productDao.addSameGood(sameGood);
		return result;
	}

	@Override
	public RetInfo getProductsHasSgForPage(int pageNum, long merchantID, long categoryID, String barcodeid,
			String title, String orderby, int pagesize) {
		RetInfo info = new RetInfo();
		info.setResult(Constants.EXCEPTION_RESULT);
		PageVo<Product> pageVo = new PageVo<Product>(pageNum);
		pageVo.setRows(pagesize);
		List<Product> list = productDao.getProductsHasSgForPage(merchantID,categoryID,barcodeid,title,orderby,pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		info.setObject(pageVo);
		info.setResult(Constants.SUCCESS_RESULT);
		return info;
	}

	@Override
	public int getProductsSgByCount(long merchantID, long categoryID, String barcodeid, String title,
			String orderby) {
		return productDao.getProductsSgByCount(merchantID,categoryID,barcodeid,title,orderby);
	}

	@Override
	public List<Product> getSameGoods(long merchantID, long categoryID, String barcodeid, String title) {
		return productDao.getSameGoods(merchantID, categoryID, barcodeid, title);
	}

	@Override
	public int updateProductTop(long pid, int istop) {
		return productDao.updateProductTop(pid, istop);
	}

	@Override
	public int deleteSameGoods(String barcodeId,String sid) {
		return productDao.deleteSameGoods(barcodeId,sid);
	}

	@Override
	public SameGood getSameGood(long mid, String barcodeid) {
		return productDao.getSameGood(mid, barcodeid);
	}

	@Override
	public int addSameGood(SameGood sameGood) {
		return productDao.addSameGood(sameGood);
	}

	@Override
	public List<Product> getProducts(long merchantID, long categoryID, String barcodeId, String title, String orderby) {
		
//		SqlSessionFactory sqlSessionFactory = MybatisUtils.getSessionFactory("spring-mybatis.xml");
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		productDao = sqlSession.getMapper(ProductDao.class);
//		List<Product> productList = productDao.getProducts(merchantID, categoryID, barcodeId, title, orderby);
//		sqlSession.insert("getProducts", productList);  
//		sqlSession.commit(); 
		
		return productDao.getProducts(merchantID, categoryID, barcodeId, title, orderby);
	}

	@Override
	public List<Product> getProductsHasSg(long merchantID, long categoryID, String barcodeId, String title,
			String orderby) {
		return productDao.getProductsHasSg(merchantID, categoryID, barcodeId, title, orderby);
	}

}
