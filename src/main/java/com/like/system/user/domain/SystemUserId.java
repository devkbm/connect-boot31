package com.like.system.user.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemUserId implements Serializable {
	
	private static final long serialVersionUID = -8848284684753048121L;

	@Column(name="ORG_CD")
	private String organizationCode;
	
	@Column(name="STAFF_NO")
	private String staffNo;

	protected SystemUserId() {}
	
	protected SystemUserId(String organizationCode, String staffNo) {
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
