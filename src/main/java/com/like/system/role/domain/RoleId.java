package com.like.system.role.domain;

import java.util.Objects;

public class RoleId {

	String organizationCode;
	
	String roleCode;
	
	public RoleId(String organizationCode, String roleCode) {
		this.organizationCode = organizationCode;
		this.roleCode = roleCode;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleCode, organizationCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleId other = (RoleId) obj;
		return Objects.equals(roleCode, other.roleCode)
				&& Objects.equals(organizationCode, other.organizationCode);
	}
	
	
}
