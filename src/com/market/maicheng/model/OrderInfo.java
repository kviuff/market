package com.market.maicheng.model;

public class OrderInfo {
    private Long id;
    /**
     * 送货地址ID
     */
    private Long addressid;
    /**
     * 支付方式 0微信 1支付宝
     */
    private int paymethod;
    /**
     * 配送方式
     */
    private String shippingmethod;
    /**
     * 发票类型 0 不开发票 1 普通发票 2增值税发票
     */
    private int invoicetypename;
    /**
     * 发票抬头
     */
    private String invoicecontent;
    /**
     * 商品金额
     */
    private Double pmoney;
    /**
     * 运费
     */
    private Double distribution;
    /**
     * 产品名称
     */
    private String pname;
    /**
     * 产品数量
     */
    private int pcount;
    /**
     * 订单备注
     */
    private String content;
    /**
     * 是否支付：0未支付 1已支付 2 已取消 3 已退款 4 退款中 5 驳回退款 6 发货中 7 已完成
     */
    private int ispay;
    /**
     * 支付时间/取消时间
     */
    private Long paytime;
    /**
     * 下单时间
     */
    private Long addtime;
    /**
     * 下单人ID
     */
    private Long userid;
    /**
     * 快递单号
     */
    private String expressnum;
    /**
     * 订单来源：0pc端 2手机端
     */
    private int ordersource;

    private int isdel;
    /**
     * 图片地址
     */
    private String purl;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 省
     */
    private Long provinceid;

    private String provincename;
    /**
     * 市
     */
    private Long cityid;

    private String cityname;
    /**
     * 区
     */
    private Long regionid;

    private String region;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 产品ID
     */
    private Long pid;
    /**
     * 退款原因
     */
    private String rreason;
    /**
     * 子订单号
     */
    private Long subid;
    /**
     * 子订单的产品单价
     */
    private String subprice;
    /**
     * 是否拼单 0不1是
     */
    private int ispin;
    /**
     * 销售ID
     */
    private Long saleid;
    /**
     * 销售姓名
     */
    private String salename;
    /**
     * 条形码
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
     * 商铺ID
     */
    private long merchantid;
    /**
     * 商铺名
     */
    private String merchantname;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * 是否审核 0未审核 1审核
     */
    private int isexamine;
    
    /**
     * 用户名
     */
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressid() {
        return addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    public int getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(int paymethod) {
        this.paymethod = paymethod;
    }

    public String getShippingmethod() {
        return shippingmethod;
    }

    public void setShippingmethod(String shippingmethod) {
        this.shippingmethod = shippingmethod == null ? null : shippingmethod.trim();
    }

    public int getInvoicetypename() {
        return invoicetypename;
    }

    public void setInvoicetypename(int invoicetypename) {
        this.invoicetypename = invoicetypename;
    }

    public String getInvoicecontent() {
        return invoicecontent;
    }

    public void setInvoicecontent(String invoicecontent) {
        this.invoicecontent = invoicecontent == null ? null : invoicecontent.trim();
    }

    public Double getPmoney() {
        return pmoney;
    }

    public void setPmoney(Double pmoney) {
        this.pmoney = pmoney;
    }

    public Double getDistribution() {
        return distribution;
    }

    public void setDistribution(Double distribution) {
        this.distribution = distribution;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public int getIspay() {
        return ispay;
    }

    public void setIspay(int ispay) {
        this.ispay = ispay;
    }

    public Long getPaytime() {
        return paytime;
    }

    public void setPaytime(Long paytime) {
        this.paytime = paytime;
    }

    public Long getAddtime() {
        return addtime;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getExpressnum() {
        return expressnum;
    }

    public void setExpressnum(String expressnum) {
        this.expressnum = expressnum == null ? null : expressnum.trim();
    }

    public int getOrdersource() {
        return ordersource;
    }

    public void setOrdersource(int ordersource) {
        this.ordersource = ordersource;
    }

    public int getIsdel() {
        return isdel;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl == null ? null : purl.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Long getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename == null ? null : provincename.trim();
    }

    public Long getCityid() {
        return cityid;
    }

    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public Long getRegionid() {
        return regionid;
    }

    public void setRegionid(Long regionid) {
        this.regionid = regionid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getRreason() {
        return rreason;
    }

    public void setRreason(String rreason) {
        this.rreason = rreason == null ? null : rreason.trim();
    }

    public Long getSubid() {
        return subid;
    }

    public void setSubid(Long subid) {
        this.subid = subid;
    }

    public String getSubprice() {
        return subprice;
    }

    public void setSubprice(String subprice) {
        this.subprice = subprice == null ? null : subprice.trim();
    }

    public int getIspin() {
        return ispin;
    }

    public void setIspin(int ispin) {
        this.ispin = ispin;
    }

    public Long getSaleid() {
        return saleid;
    }

    public void setSaleid(Long saleid) {
        this.saleid = saleid;
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

	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
	}

	public long getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(long merchantid) {
		this.merchantid = merchantid;
	}

	

	public String getMerchantname() {
		return merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsexamine() {
		return isexamine;
	}

	public void setIsexamine(int isexamine) {
		this.isexamine = isexamine;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
    
}