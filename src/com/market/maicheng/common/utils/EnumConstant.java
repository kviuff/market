package com.market.maicheng.common.utils;

public enum EnumConstant {
	正常("adminUserUnlock", 0),
	锁定("adminUserUnlock", 1),
	;
	
	public final int num;
	public final String type;

	public  final int DEFAULT_PAGE_SIZE = 2000;

	public int getNum() {
		return this.num;
	}
	public String getType() {
		return this.type;
	}
	private EnumConstant(String type, int title) {
		this.num = title;
		this.type = type;
	}
	public static EnumConstant getEnumConstant(String type, int num) {
		for(EnumConstant ec : EnumConstant.values()) {
			if (ec.getType().equalsIgnoreCase(type) && ec.getNum() == num)
				return ec;
		}
		return null;
	}
	
	public static EnumConstant getAdminUserUnlock(int num) {
		return getEnumConstant("adminUserUnlock", num);
	}
}
