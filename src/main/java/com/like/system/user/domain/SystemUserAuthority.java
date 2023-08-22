package com.like.system.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "COMUSERAUTHORITY")
public class SystemUserAuthority {

	@Id
	@Column(name="ORG_CD")
	String organizationCode;
	
	@Id
	@Column(name="USER_ID")
	String userId;
	
	@Id
	@Column(name="AUTH_CD")
	String authorityCode;
	
	public SystemUserAuthority(String organizationCode, String userId, String authorityCode) {
		this.organizationCode = organizationCode;
		this.userId = userId;
		this.authorityCode = authorityCode;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getUserId() {
		return userId;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	
}
