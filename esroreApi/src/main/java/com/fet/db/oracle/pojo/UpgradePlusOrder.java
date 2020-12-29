package com.fet.db.oracle.pojo;
// Generated 2020/12/25 �U�� 02:18:50 by Hibernate Tools 4.3.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UpgradePlusOrder generated by hbm2java
 */
@Entity
@Table(name = "UPGRADE_PLUS_ORDER")
public class UpgradePlusOrder implements java.io.Serializable {

	private UpgradePlusOrderId id;

	public UpgradePlusOrder() {
	}

	public UpgradePlusOrder(UpgradePlusOrderId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "cono", column = @Column(name = "CONO", length = 20)),
			@AttributeOverride(name = "txid", column = @Column(name = "TXID", length = 32)),
			@AttributeOverride(name = "subscriberId", column = @Column(name = "SUBSCRIBER_ID", length = 20)),
			@AttributeOverride(name = "accountId", column = @Column(name = "ACCOUNT_ID", length = 20)),
			@AttributeOverride(name = "userName", column = @Column(name = "USER_NAME", length = 10)),
			@AttributeOverride(name = "rocId", column = @Column(name = "ROC_ID", length = 10)),
			@AttributeOverride(name = "onsalePromoListId", column = @Column(name = "ONSALE_PROMO_LIST_ID", length = 32)),
			@AttributeOverride(name = "msisdn", column = @Column(name = "MSISDN", length = 10)),
			@AttributeOverride(name = "isSendToLoyalty", column = @Column(name = "IS_SEND_TO_LOYALTY", length = 1)),
			@AttributeOverride(name = "coDate", column = @Column(name = "CO_DATE", length = 7)),
			@AttributeOverride(name = "loyaltyResponseXml", column = @Column(name = "LOYALTY_RESPONSE_XML", length = 4000)),
			@AttributeOverride(name = "initialOrderXml", column = @Column(name = "INITIAL_ORDER_XML", length = 4000)),
			@AttributeOverride(name = "updateEstoreDataResponse", column = @Column(name = "UPDATE_ESTORE_DATA_RESPONSE", length = 4000)),
			@AttributeOverride(name = "lyConfirmOrderRequestXml", column = @Column(name = "LY_CONFIRM_ORDER_REQUEST_XML", length = 4000)),
			@AttributeOverride(name = "lyConfirmOrderResponseXml", column = @Column(name = "LY_CONFIRM_ORDER_RESPONSE_XML", length = 4000)),
			@AttributeOverride(name = "cohOrderId", column = @Column(name = "COH_ORDER_ID", length = 32)) })
	public UpgradePlusOrderId getId() {
		return this.id;
	}

	public void setId(UpgradePlusOrderId id) {
		this.id = id;
	}

}