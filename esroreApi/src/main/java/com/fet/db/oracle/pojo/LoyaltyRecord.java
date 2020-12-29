package com.fet.db.oracle.pojo;
// Generated 2020/12/25 �U�� 02:18:50 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LoyaltyRecord generated by hbm2java
 */
@Entity
@Table(name = "LOYALTY_RECORD")
public class LoyaltyRecord implements java.io.Serializable {

	private String id;
	private String msisdn;
	private Character passCheck;
	private String entryPoint;
	private String channel;
	private String cono;
	private Date createTime;
	private String rocid;
	private String errorMessage;
	private Character notifyMe;
	private Long arpb;
	private String msisdnStatus;
	private Date contractEnddate;
	private String os;
	private String iceRule;
	private String iceDescription;
	private String iceErrorMessage;

	public LoyaltyRecord() {
	}

	public LoyaltyRecord(String id) {
		this.id = id;
	}

	public LoyaltyRecord(String id, String msisdn, Character passCheck, String entryPoint, String channel, String cono,
			Date createTime, String rocid, String errorMessage, Character notifyMe, Long arpb, String msisdnStatus,
			Date contractEnddate, String os, String iceRule, String iceDescription, String iceErrorMessage) {
		this.id = id;
		this.msisdn = msisdn;
		this.passCheck = passCheck;
		this.entryPoint = entryPoint;
		this.channel = channel;
		this.cono = cono;
		this.createTime = createTime;
		this.rocid = rocid;
		this.errorMessage = errorMessage;
		this.notifyMe = notifyMe;
		this.arpb = arpb;
		this.msisdnStatus = msisdnStatus;
		this.contractEnddate = contractEnddate;
		this.os = os;
		this.iceRule = iceRule;
		this.iceDescription = iceDescription;
		this.iceErrorMessage = iceErrorMessage;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "MSISDN", length = 10)
	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Column(name = "PASS_CHECK", length = 1)
	public Character getPassCheck() {
		return this.passCheck;
	}

	public void setPassCheck(Character passCheck) {
		this.passCheck = passCheck;
	}

	@Column(name = "ENTRY_POINT", length = 20)
	public String getEntryPoint() {
		return this.entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	@Column(name = "CHANNEL", length = 10)
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "CONO", length = 20)
	public String getCono() {
		return this.cono;
	}

	public void setCono(String cono) {
		this.cono = cono;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ROCID", length = 10)
	public String getRocid() {
		return this.rocid;
	}

	public void setRocid(String rocid) {
		this.rocid = rocid;
	}

	@Column(name = "ERROR_MESSAGE", length = 4000)
	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Column(name = "NOTIFY_ME", length = 1)
	public Character getNotifyMe() {
		return this.notifyMe;
	}

	public void setNotifyMe(Character notifyMe) {
		this.notifyMe = notifyMe;
	}

	@Column(name = "ARPB", precision = 10, scale = 0)
	public Long getArpb() {
		return this.arpb;
	}

	public void setArpb(Long arpb) {
		this.arpb = arpb;
	}

	@Column(name = "MSISDN_STATUS", length = 20)
	public String getMsisdnStatus() {
		return this.msisdnStatus;
	}

	public void setMsisdnStatus(String msisdnStatus) {
		this.msisdnStatus = msisdnStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CONTRACT_ENDDATE", length = 7)
	public Date getContractEnddate() {
		return this.contractEnddate;
	}

	public void setContractEnddate(Date contractEnddate) {
		this.contractEnddate = contractEnddate;
	}

	@Column(name = "OS", length = 20)
	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Column(name = "ICE_RULE", length = 10)
	public String getIceRule() {
		return this.iceRule;
	}

	public void setIceRule(String iceRule) {
		this.iceRule = iceRule;
	}

	@Column(name = "ICE_DESCRIPTION", length = 4000)
	public String getIceDescription() {
		return this.iceDescription;
	}

	public void setIceDescription(String iceDescription) {
		this.iceDescription = iceDescription;
	}

	@Column(name = "ICE_ERROR_MESSAGE", length = 4000)
	public String getIceErrorMessage() {
		return this.iceErrorMessage;
	}

	public void setIceErrorMessage(String iceErrorMessage) {
		this.iceErrorMessage = iceErrorMessage;
	}

}