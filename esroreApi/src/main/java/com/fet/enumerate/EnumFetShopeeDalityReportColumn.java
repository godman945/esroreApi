package com.fet.enumerate;

public enum EnumFetShopeeDalityReportColumn {
	
	ORDER_NO("shopee訂單編號","ORDER_NO"),
	PCODE("申辦方案名稱(英文)","PCODE"),
	USER_MOBILE("聯絡方式","USER_MOBILE"),
	ORDER_TYPE("shopee訂單型態代碼","ORDER_TYPE"),
	MSISDN("門號","MSISDN"),
	ONSALE_NAME("申辦方案名稱(中文)","ONSALE_NAME"),
	PHONE_NAME("申辦手機型號","PHONE_NAME"),
	FETNO("申辦手機料號","FETNO"),
	PREPAYMENT("預繳金","PREPAYMENT"),
	PRODUCT_PRICE("專案手機價","PRODUCT_PRICE"),
	TOTAL_AMOUNT("訂單總金額(預繳金+專案手機價)","TOTAL_AMOUNT"),
	CROSS_COOPERATION_ORDER_STATUS("shopee訂單狀態","CROSS_COOPERATION_ORDER_STATUS"),
	MASTER_CO_DATE("遠傳訂單成立日期","MASTER_CO_DATE"),
	USER_NAME("客戶姓名","USER_NAME"),
	CONO("遠傳訂單編號","CONO"),
	QUANTITY_FLAG("是否有貨","QUANTITY_FLAG"),
	MASTER_CO_STATUS("遠傳訂單狀態","MASTER_CO_STATUS"),
	MASTER_ACTIVATION_DATE("啟用日期(D天)","MASTER_ACTIVATION_DATE"),
	MASTER_CO_TYPE("NP狀態 ","MASTER_NP_STATUS"),
	ACTIVATION_DATE_ADD_10("鑑賞期due day(D+10天)","ACTIVATION_DATE_ADD_10"),
	MASTER_OVER_ACTIVATION_DATE("已過鑑賞期","MASTER_OVER_ACTIVATION_DATE");
	
		
	
	private final String name;
	private final String column;

	private EnumFetShopeeDalityReportColumn(String name,String column) {
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
