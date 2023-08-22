package com.like.system.user.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemUserId implements Serializable {

	private static final long serialVersionUID = -8544637739358675046L;

	@Column(name="ORG_CD")
	String organizationCode;
	
	@Column(name="USER_ID")
	String userId;
	
	protected SystemUserId() {}
	
	public SystemUserId(String organizationCode, String userId) {
		this.organizationCode = organizationCode;
		this.userId = userId;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getUserId() {
		return userId;
	}
		
}
