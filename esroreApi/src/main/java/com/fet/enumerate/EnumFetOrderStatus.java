package com.fet.enumerate;

public enum EnumFetOrderStatus {
	
	FET_TI("TI","訂單成立"),
	FET_BO("BO","出貨準備中"),
	FET_BD("BD","物流配送中"),
	FET_BCS("BCS","超商配送中"),
	FET_TGR("TGR","門號已開通"),
	FET_TGA("TGA","訂單確認完成"),
	FET_CNL("CNL","取消訂單(退貨退款處理中)"),
	FET_CNL24("CNL24","取消訂單(24H未填URL)");
		
	
	private final String type;
	private final String name;

	private EnumFetOrderStatus(String type, String name) {
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
