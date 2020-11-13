package com.fet.enumerate;

public enum EnumFetIaStatus {
	
	FET_I("I","訂單成立"),
	FET_D("D","訂單完成");
	
	private final String type;
	private final String name;

	private EnumFetIaStatus(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	
}
