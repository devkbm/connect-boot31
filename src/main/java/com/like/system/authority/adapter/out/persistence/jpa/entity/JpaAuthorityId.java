package com.like.system.authority.adapter.out.persistence.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class JpaAuthorityId implements Serializable {
		
	private static final long serialVersionUID = 6334573408608466739L;

	@Column(name="ORG_CD")
	String organizationCode;
	
	@Column(name="AUTH_CD")
	String authorityCode;

	protected JpaAuthorityId() {}
	
	public JpaAuthorityId(String organizationCode, String authorityCode) {
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
