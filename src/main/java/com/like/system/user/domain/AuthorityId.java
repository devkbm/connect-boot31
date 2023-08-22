package com.like.system.user.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Setter;

@Setter
@Embeddable
public class AuthorityId implements Serializable {
		
	private static final long serialVersionUID = 6334573408608466739L;

	@Column(name="ORG_CD")
	String organizationCode;
	
	@Column(name="AUTH_CD")
	String authorityCode;

	protected AuthorityId() {}
	
	public AuthorityId(String organizationCode, String authorityCode) {
		this.organizationCode = organizationCode;
		this.authorityCode = authorityCode;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}
	
	
}
