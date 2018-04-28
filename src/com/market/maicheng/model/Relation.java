package com.market.maicheng.model;
/**
 * 客户关系表
 * @author Administrator
 *
 */
public class Relation {
    private Long id;

    private Long createid;

    private Long relaid;
    
    private Long classid;

    private Long createtime;

    private String contacts;

    private String mobile;

    private Integer fromType;

    private Integer isdel;
    private Long mid;
    
    private Integer pricLevel;
    
    public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Integer getPricLevel() {
		return pricLevel;
	}

	public void setPricLevel(Integer pricLevel) {
		this.pricLevel = pricLevel;
	}

	

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateid() {
        return createid;
    }

    public void setCreateid(Long createid) {
        this.createid = createid;
    }

    public Long getRelaid() {
        return relaid;
    }

    public void setRelaid(Long relaid) {
        this.relaid = relaid;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

	public Long getClassid() {
		return classid;
	}

	public void setClassid(Long classid) {
		this.classid = classid;
	}
}