package com.like.system.role.adapter.out.persistence.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class JpaRoleId implements Serializable {
		
	private static final long serialVersionUID = 6334573408608466739L;

	@Column(name="ORG_CD")
	String organizationCode;
	
	@Column(name="ROLE_CD")
	String roleCode;

	protected JpaRoleId() {}
	
	public JpaRoleId(String organizationCode, String roleCode) {
		this.organizationCode = organizationCode;
		this.roleCode = roleCode;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getRoleCode() {
		return roleCode;
	}
	
	
}
