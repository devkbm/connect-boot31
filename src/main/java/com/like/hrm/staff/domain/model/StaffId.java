package com.like.hrm.staff.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class StaffId implements Serializable {

	private static final long serialVersionUID = 6064949234611151198L;

	@Column(name="ORG_CD")
	private String organizationCode;
	
	@Column(name="STAFF_NO")
	private String staffNo;

	protected StaffId() {}
	
	public StaffId(String organizationCode, String staffNo) {
		this.organizationCode = organizationCode;
		this.staffNo = staffNo;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getStaffNo() {
		return staffNo;
	}		
}
