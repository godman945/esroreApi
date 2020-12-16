package com.fet.db.oracle.pojo;
// Generated 2020/12/15 �W�� 11:05:39 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CrossCooperation generated by hbm2java
 */
@Entity
@Table(name = "CROSS_COOPERATION", schema = "ES_DEV")
public class CrossCooperation implements java.io.Serializable {

	private String orderNo;
	private String userName;
	private String userMobile;
	private String orderType;
	private String msisdn;
	private String pcode;
	private String onsaleName;
	private String productName;
	private String fetno;
	private long productPrice;
	private long prepayment;
	private long totalAmount;
	private String cancelFlag;
	private String smsUrl;
	private String cono;
	private Date createDate;
	private String createTimestamp;
	private Date updateDate;
	private String SOrderNo;
	private String itemId;
	private String modelId;
	private String iaStatus;
	private String coStatus;
	private String orderStatus;

	public CrossCooperation() {
	}

	public CrossCooperation(String orderNo, long productPrice, long prepayment, long totalAmount) {
		this.orderNo = orderNo;
		this.productPrice = productPrice;
		this.prepayment = prepayment;
		this.totalAmount = totalAmount;
	}

	public CrossCooperation(String orderNo, String userName, String userMobile, String orderType, String msisdn,
			String pcode, String onsaleName, String productName, String fetno, long productPrice, long prepayment,
			long totalAmount, String cancelFlag, String smsUrl, String cono, Date createDate, String createTimestamp,
			Date updateDate, String SOrderNo, String itemId, String modelId, String iaStatus, String coStatus,
			String orderStatus) {
		this.orderNo = orderNo;
		this.userName = userName;
		this.userMobile = userMobile;
		this.orderType = orderType;
		this.msisdn = msisdn;
		this.pcode = pcode;
		this.onsaleName = onsaleName;
		this.productName = productName;
		this.fetno = fetno;
		this.productPrice = productPrice;
		this.prepayment = prepayment;
		this.totalAmount = totalAmount;
		this.cancelFlag = cancelFlag;
		this.smsUrl = smsUrl;
		this.cono = cono;
		this.createDate = createDate;
		this.createTimestamp = createTimestamp;
		this.updateDate = updateDate;
		this.SOrderNo = SOrderNo;
		this.itemId = itemId;
		this.modelId = modelId;
		this.iaStatus = iaStatus;
		this.coStatus = coStatus;
		this.orderStatus = orderStatus;
	}

	@Id

	@Column(name = "ORDER_NO", unique = true, nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "USER_NAME", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_MOBILE", length = 10)
	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	@Column(name = "ORDER_TYPE", length = 2)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "MSISDN", length = 10)
	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Column(name = "PCODE", length = 50)
	public String getPcode() {
		return this.pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	@Column(name = "ONSALE_NAME", length = 100)
	public String getOnsaleName() {
		return this.onsaleName;
	}

	public void setOnsaleName(String onsaleName) {
		this.onsaleName = onsaleName;
	}

	@Column(name = "PRODUCT_NAME", length = 200)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "FETNO", length = 32)
	public String getFetno() {
		return this.fetno;
	}

	public void setFetno(String fetno) {
		this.fetno = fetno;
	}

	@Column(name = "PRODUCT_PRICE", nullable = false, precision = 11, scale = 0)
	public long getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "PREPAYMENT", nullable = false, precision = 11, scale = 0)
	public long getPrepayment() {
		return this.prepayment;
	}

	public void setPrepayment(long prepayment) {
		this.prepayment = prepayment;
	}

	@Column(name = "TOTAL_AMOUNT", nullable = false, precision = 11, scale = 0)
	public long getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "CANCEL_FLAG", length = 1)
	public String getCancelFlag() {
		return this.cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	@Column(name = "SMS_URL", length = 200)
	public String getSmsUrl() {
		return this.smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	@Column(name = "CONO", length = 20)
	public String getCono() {
		return this.cono;
	}

	public void setCono(String cono) {
		this.cono = cono;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_TIMESTAMP", length = 20)
	public String getCreateTimestamp() {
		return this.createTimestamp;
	}

	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 7)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "S_ORDER_NO", length = 200)
	public String getSOrderNo() {
		return this.SOrderNo;
	}

	public void setSOrderNo(String SOrderNo) {
		this.SOrderNo = SOrderNo;
	}

	@Column(name = "ITEM_ID", length = 20)
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name = "MODEL_ID", length = 20)
	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	@Column(name = "IA_STATUS", length = 5)
	public String getIaStatus() {
		return this.iaStatus;
	}

	public void setIaStatus(String iaStatus) {
		this.iaStatus = iaStatus;
	}

	@Column(name = "CO_STATUS", length = 5)
	public String getCoStatus() {
		return this.coStatus;
	}

	public void setCoStatus(String coStatus) {
		this.coStatus = coStatus;
	}

	@Column(name = "ORDER_STATUS", length = 5)
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
