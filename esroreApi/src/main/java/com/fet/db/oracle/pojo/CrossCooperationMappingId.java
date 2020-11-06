// default package
// Generated 2020/11/6 �U�� 06:23:13 by Hibernate Tools 4.0.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CrossCooperationMappingId generated by hbm2java
 */
@Embeddable
public class CrossCooperationMappingId implements java.io.Serializable {

	private String itemId;
	private String moduleId;
	private String pcode;
	private String onsaleName;
	private String productName;
	private String fetno;
	private long productPrice;
	private long prepayment;

	public CrossCooperationMappingId() {
	}

	public CrossCooperationMappingId(long productPrice, long prepayment) {
		this.productPrice = productPrice;
		this.prepayment = prepayment;
	}

	public CrossCooperationMappingId(String itemId, String moduleId, String pcode, String onsaleName,
			String productName, String fetno, long productPrice, long prepayment) {
		this.itemId = itemId;
		this.moduleId = moduleId;
		this.pcode = pcode;
		this.onsaleName = onsaleName;
		this.productName = productName;
		this.fetno = fetno;
		this.productPrice = productPrice;
		this.prepayment = prepayment;
	}

	@Column(name = "ITEM_ID", length = 20)
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name = "MODULE_ID", length = 20)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CrossCooperationMappingId))
			return false;
		CrossCooperationMappingId castOther = (CrossCooperationMappingId) other;

		return ((this.getItemId() == castOther.getItemId()) || (this.getItemId() != null
				&& castOther.getItemId() != null && this.getItemId().equals(castOther.getItemId())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this.getModuleId() != null
						&& castOther.getModuleId() != null && this.getModuleId().equals(castOther.getModuleId())))
				&& ((this.getPcode() == castOther.getPcode()) || (this.getPcode() != null
						&& castOther.getPcode() != null && this.getPcode().equals(castOther.getPcode())))
				&& ((this.getOnsaleName() == castOther.getOnsaleName()) || (this.getOnsaleName() != null
						&& castOther.getOnsaleName() != null && this.getOnsaleName().equals(castOther.getOnsaleName())))
				&& ((this.getProductName() == castOther.getProductName())
						|| (this.getProductName() != null && castOther.getProductName() != null
								&& this.getProductName().equals(castOther.getProductName())))
				&& ((this.getFetno() == castOther.getFetno()) || (this.getFetno() != null
						&& castOther.getFetno() != null && this.getFetno().equals(castOther.getFetno())))
				&& (this.getProductPrice() == castOther.getProductPrice())
				&& (this.getPrepayment() == castOther.getPrepayment());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getItemId() == null ? 0 : this.getItemId().hashCode());
		result = 37 * result + (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result + (getPcode() == null ? 0 : this.getPcode().hashCode());
		result = 37 * result + (getOnsaleName() == null ? 0 : this.getOnsaleName().hashCode());
		result = 37 * result + (getProductName() == null ? 0 : this.getProductName().hashCode());
		result = 37 * result + (getFetno() == null ? 0 : this.getFetno().hashCode());
		result = 37 * result + (int) this.getProductPrice();
		result = 37 * result + (int) this.getPrepayment();
		return result;
	}

}