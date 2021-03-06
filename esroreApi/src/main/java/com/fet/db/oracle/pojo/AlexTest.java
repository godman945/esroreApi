package com.fet.db.oracle.pojo;
// Generated 2020/11/9 �U�� 10:18:55 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AlexTest generated by hbm2java
 */
@Entity
@Table(name = "ALEX_TEST", schema = "SYSTEM")
public class AlexTest implements java.io.Serializable {

	private String orderNo;
	private String userName;
	private String userMobile;
	private String orderType;
	private String msisdn;
	private String cono;
	private String cancelFlag;
	private Date createTime;

	public AlexTest() {
	}

	public AlexTest(String orderNo) {
		this.orderNo = orderNo;
	}

	public AlexTest(String orderNo, String userName, String userMobile, String orderType, String msisdn, String cono,
			String cancelFlag, Date createTime) {
		this.orderNo = orderNo;
		this.userName = userName;
		this.userMobile = userMobile;
		this.orderType = orderType;
		this.msisdn = msisdn;
		this.cono = cono;
		this.cancelFlag = cancelFlag;
		this.createTime = createTime;
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

	@Column(name = "USER_MOBILE", length = 20)
	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	@Column(name = "ORDER_TYPE", length = 20)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "MSISDN", length = 20)
	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Column(name = "CONO", length = 20)
	public String getCono() {
		return this.cono;
	}

	public void setCono(String cono) {
		this.cono = cono;
	}

	@Column(name = "CANCEL_FLAG", length = 1)
	public String getCancelFlag() {
		return this.cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
