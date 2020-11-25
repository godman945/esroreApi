package com.fet.enumerate;

public enum EnumFetShopeeFetNoDalityReportColumn {
	
	ORDER_NO("遠傳商品料號","FET_NO"),
	PCODE("遠傳型號","NAME"),
	USER_MOBILE("庫存量","INVENTORY");
	
		
	
	private final String name;
	private final String column;

	private EnumFetShopeeFetNoDalityReportColumn(String name,String column) {
		this.name = name;
		this.column = column;
	}


	public String getName() {
		return name;
	}


	public String getColumn() {
		return column;
	}
	
	
}
