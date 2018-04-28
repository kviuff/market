package com.market.maicheng.model;
/**
 * 商品库存表
 * @author gaohf
 *
 */
public class AbGoods {

	/**
	 * 商品id
	 */
	private int goodsId;
	/**
	 * 商品名称
	 */
	private String goodsName;
//	/**
//	 * 商品广告词
//	 */
//	private String goodsJingle;
	/**
	 * 商品分类
	 */
	private int gcId;
	/**
	 * 商品一级分类
	 */
	private int gcId1;
	/**
	 * 商品二级分类
	 */
	private int gcId2;

	/**
	 * 商品三级分类
	 */
	private int gcId3;
	/**
	 * 品牌id
	 */
	private int brandId;
	/**
	 * 品名
	 */
	private String brandName;
//	/**
//	 * 商品价格
//	 */
//	private double goodsPrice;
//	
//	/**
//	 *  商品促销价格
//	 */
//	private double goodsPromotionPrice;
//	
//	/**
//	 * 商品市场价
//	 */
//	private double goodsMarketPrice;
	
	/**
	 * 条形码
	 */
	private String goodsBarcode;
//	/**
//	 * 规格名称
//	 */
//	private String specName;
	/**
	 * 规格
	 */
	private String tgItem;
//	/**
//	 * 商品规格序列化
//	 */
//	private String goodsSpec;
//	/**
//	 * 规格序列化单位
//	 */
//	private String goodsSpecUnit;
	/**
	 * 商品添加时间
	 */
	private int goodsAddtime;
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGcId() {
		return gcId;
	}
	public void setGcId(int gcId) {
		this.gcId = gcId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
//	public double getGoodsPrice() {
//		return goodsPrice;
//	}
//	public void setGoodsPrice(double goodsPrice) {
//		this.goodsPrice = goodsPrice;
//	}
//	public double getGoodsPromotionPrice() {
//		return goodsPromotionPrice;
//	}
//	public void setGoodsPromotionPrice(double goodsPromotionPrice) {
//		this.goodsPromotionPrice = goodsPromotionPrice;
//	}
//	public double getGoodsMarketPrice() {
//		return goodsMarketPrice;
//	}
//	public void setGoodsMarketPrice(double goodsMarketPrice) {
//		this.goodsMarketPrice = goodsMarketPrice;
//	}
	public String getGoodsBarcode() {
		return goodsBarcode;
	}
	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}
//	public String getSpecName() {
//		return specName;
//	}
//	public void setSpecName(String specName) {
//		this.specName = specName;
//	}
//	public String getGoodsSpec() {
//		return goodsSpec;
//	}
//	public void setGoodsSpec(String goodsSpec) {
//		this.goodsSpec = goodsSpec;
//	}
//	public String getGoodsSpecUnit() {
//		return goodsSpecUnit;
//	}
//	public void setGoodsSpecUnit(String goodsSpecUnit) {
//		this.goodsSpecUnit = goodsSpecUnit;
//	}
	public int getGoodsAddtime() {
		return goodsAddtime;
	}
	public void setGoodsAddtime(int goodsAddtime) {
		this.goodsAddtime = goodsAddtime;
	}
	public int getGcId1() {
		return gcId1;
	}
	public void setGcId1(int gcId1) {
		this.gcId1 = gcId1;
	}
	public int getGcId2() {
		return gcId2;
	}
	public void setGcId2(int gcId2) {
		this.gcId2 = gcId2;
	}
	public int getGcId3() {
		return gcId3;
	}
	public void setGcId3(int gcId3) {
		this.gcId3 = gcId3;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getTgItem() {
		return tgItem;
	}
	public void setTgItem(String tgItem) {
		this.tgItem = tgItem;
	}
}
