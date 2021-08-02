package models;

import java.sql.Timestamp;

//import java.security.Timestamp;

public class Reimbursement {

	private int reimId;
	private double reimAmount;
	private Timestamp reimSubmitted;
	private Timestamp reimResolved = null;
	private String reimDescription = null;
	//private String reimReceipt;
	private User reimAuthorId;
	private User reimResolverId;
	private ReimbursementStatus reimStatusId;
	private ReimbursementType reimTypeId;

	
	public Reimbursement() {
		super();
	}
	public Reimbursement(int reimId, double reimAmount, Timestamp reimSubmitted, Timestamp reimResolved,
			String reimDescription, User reimAuthorId, User reimResolverId, ReimbursementStatus reimStatus, ReimbursementType reimType) {
		super();
		this.reimId = reimId;
		this.reimAmount = reimAmount;
		this.reimSubmitted = reimSubmitted;
		this.reimResolved = reimResolved;
		this.reimDescription = reimDescription;
		//this.reimReceipt = reimReceipt;
		this.reimAuthorId = reimAuthorId;
		this.reimResolverId = reimResolverId;
		this.reimStatusId = reimStatus;
		this.reimTypeId = reimType;
	}
	public int getReimId() {
		return reimId;
	}
	public void setReimId(int reimId) {
		this.reimId = reimId;
	}
	public double getReimAmount() {
		return reimAmount;
	}
	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}
	public Timestamp getReimSubmitted() {
		return reimSubmitted;
	}
	public void setReimSubmitted(Timestamp reimSubmitted) {
		this.reimSubmitted = reimSubmitted;
	}
	public Timestamp getReimResolved() {
		return reimResolved;
	}
	public void setReimResolved(Timestamp reimResolved) {
		this.reimResolved = reimResolved;
	}
	public String getReimDescription() {
		return reimDescription;
	}
	public void setReimDescription(String reimDescription) {
		this.reimDescription = reimDescription;
	}
//	public String getReimReceipt() {
//		return reimReceipt;
//	}
//	public void setReimReceipt(String reimReceipt) {
//		this.reimReceipt = reimReceipt;
//	}
	
	public User getReimAuthorId() {
		return reimAuthorId;
	}
	public void setReimAuthorId(User reimAuthorId) {
		this.reimAuthorId = reimAuthorId;
	}
	public User getReimResolverId() {
		return reimResolverId;
	}
	public void setReimResolverId(User reimResolverId) {
		this.reimResolverId = reimResolverId;
	}
	public ReimbursementStatus getReimStatusId() {
		return reimStatusId;
	}
	public void setReimStatusId(ReimbursementStatus reimStatusId) {
		this.reimStatusId = reimStatusId;
	}
	public ReimbursementType getReimTypeId() {
		return reimTypeId;
	}
	public void setReimTypeId(ReimbursementType reimTypeId) {
		this.reimTypeId = reimTypeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimAuthorId == null) ? 0 : reimAuthorId.hashCode());
		result = prime * result + ((reimDescription == null) ? 0 : reimDescription.hashCode());
		result = prime * result + reimId;
		result = prime * result + ((reimResolved == null) ? 0 : reimResolved.hashCode());
		result = prime * result + ((reimResolverId == null) ? 0 : reimResolverId.hashCode());
		result = prime * result + ((reimStatusId == null) ? 0 : reimStatusId.hashCode());
		result = prime * result + ((reimSubmitted == null) ? 0 : reimSubmitted.hashCode());
		result = prime * result + ((reimTypeId == null) ? 0 : reimTypeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(reimAmount) != Double.doubleToLongBits(other.reimAmount))
			return false;
		if (reimAuthorId == null) {
			if (other.reimAuthorId != null)
				return false;
		} else if (!reimAuthorId.equals(other.reimAuthorId))
			return false;
		if (reimDescription == null) {
			if (other.reimDescription != null)
				return false;
		} else if (!reimDescription.equals(other.reimDescription))
			return false;
		if (reimId != other.reimId)
			return false;
		if (reimResolved == null) {
			if (other.reimResolved != null)
				return false;
		} else if (!reimResolved.equals(other.reimResolved))
			return false;
		if (reimResolverId == null) {
			if (other.reimResolverId != null)
				return false;
		} else if (!reimResolverId.equals(other.reimResolverId))
			return false;
		if (reimStatusId == null) {
			if (other.reimStatusId != null)
				return false;
		} else if (!reimStatusId.equals(other.reimStatusId))
			return false;
		if (reimSubmitted == null) {
			if (other.reimSubmitted != null)
				return false;
		} else if (!reimSubmitted.equals(other.reimSubmitted))
			return false;
		if (reimTypeId == null) {
			if (other.reimTypeId != null)
				return false;
		} else if (!reimTypeId.equals(other.reimTypeId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", reimAmount=" + reimAmount + ", reimSubmitted=" + reimSubmitted
				+ ", reimResolved=" + reimResolved + ", reimDescription=" + reimDescription + ", reimAuthorId="
				+ reimAuthorId + ", reimResolverId=" + reimResolverId + ", reimStatusId=" + reimStatusId
				+ ", reimTypeId=" + reimTypeId + "]";
	}
}
