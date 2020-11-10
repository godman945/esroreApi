package com.fet.db.oracle.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;


/**
 * The persistent class for the CROSS_COOPERATION database table.
 * 
 */
@Entity
@Table(name="CROSS_COOPERATION")
@NamedQuery(name="CrossCooperation.findAll", query="SELECT c FROM CrossCooperation c")
public class CrossCooperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORDER_NO")
	private String orderNo;

	@Column(name="CANCEL_FLAG")
	private String cancelFlag;

	private String cono;

	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_TIMESTAMP")
	private String createTimestamp;

	private String fetno;

	private String msisdn;

	@Column(name="ONSALE_NAME")
	private String onsaleName;

	@Column(name="ORDER_STATUS")
	private String orderStatus;

	@Column(name="ORDER_TYPE")
	private String orderType;

	private String pcode;

	private BigDecimal prepayment;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PRODUCT_PRICE")
	private BigDecimal productPrice;

	@Column(name="S_ORDER_NO")
	private String sOrderNo;

	@Column(name="SMS_URL")
	private String smsUrl;

	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name="UPDATE_DATE")
	private Date updateDate;

	@Column(name="USER_MOBILE")
	private String userMobile;

	@Column(name="USER_NAME")
	private String userName;

	public CrossCooperation() {
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCancelFlag() {
		return this.cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getCono() {
		return this.cono;
	}

	public void setCono(String cono) {
		this.cono = cono;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateTimestamp() {
		return this.createTimestamp;
	}

	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getFetno() {
		return this.fetno;
	}

	public void setFetno(String fetno) {
		this.fetno = fetno;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getOnsaleName() {
		return this.onsaleName;
	}

	public void setOnsaleName(String onsaleName) {
		this.onsaleName = onsaleName;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPcode() {
		return this.pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public BigDecimal getPrepayment() {
		return this.prepayment;
	}

	public void setPrepayment(BigDecimal prepayment) {
		this.prepayment = prepayment;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getSOrderNo() {
		return this.sOrderNo;
	}

	public void setSOrderNo(String sOrderNo) {
		this.sOrderNo = sOrderNo;
	}

	public String getSmsUrl() {
		return this.smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}