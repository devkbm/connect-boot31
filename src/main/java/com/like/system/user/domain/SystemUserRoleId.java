package com.like.system.user.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemUserRoleId implements Serializable {
		
	private static final long serialVersionUID = 8341227268289327529L;
	
	SystemUserId userId;
		
	@Column(name="ROLE_CD")
	String roleCode;

	protected SystemUserRoleId() {}
	
	public SystemUserRoleId(String organizationCode, String userId, String roleCode) {		
		this.userId = new SystemUserId(organizationCode, userId);
		this.roleCode = roleCode;
	}

	public SystemUserId getUserId() {
		return userId;
	}
	
	public String getRoleCode() {
		return roleCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleCode, userId.organizationCode, userId.userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemUserRoleId other = (SystemUserRoleId) obj;
		return Objects.equals(roleCode, other.roleCode) 
			&& Objects.equals(userId.organizationCode, other.userId.organizationCode) 
			&& Objects.equals(userId.userId, other.userId.userId);
	}
	
}
