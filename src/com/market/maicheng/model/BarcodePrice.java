package com.market.maicheng.model;

/**
 * 条形码库存价格
 * @author Administrator
 *
 */
public class BarcodePrice {
	private long id;
	/**
	 * 条码库存id
	 */
	private long barcodeid;
	/**
	 * 条码
	 */
	private String scode;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 规格
	 */
	private String spec;
	/**
	 * 进价
	 */
	private String price;
	/**
	 * 会员价
	 */
	private String vipprice;
	/**
	 * 供货商价
	 */
	private String stockprice;
	
	private int isDel;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getBarcodeid() {
		return barcodeid;
	}
	public void setBarcodeid(long barcodeid) {
		this.barcodeid = barcodeid;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVipprice() {
		return vipprice;
	}
	public void setVipprice(String vipprice) {
		this.vipprice = vipprice;
	}
	public String getStockprice() {
		return stockprice;
	}
	public void setStockprice(String stockprice) {
		this.stockprice = stockprice;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
}
