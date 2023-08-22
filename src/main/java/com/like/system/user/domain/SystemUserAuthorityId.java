package com.like.system.user.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemUserAuthorityId implements Serializable {
		
	private static final long serialVersionUID = 8341227268289327529L;
	
	SystemUserId userId;
		
	@Column(name="AUTH_CD")
	String authorityCode;

	protected SystemUserAuthorityId() {}
	
	public SystemUserAuthorityId(String organizationCode, String userId, String authorityCode) {		
		this.userId = new SystemUserId(organizationCode, userId);
		this.authorityCode = authorityCode;
	}

	public SystemUserId getUserId() {
		return userId;
	}
	
	public String getAuthorityCode() {
		return authorityCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorityCode, userId.organizationCode, userId.userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemUserAuthorityId other = (SystemUserAuthorityId) obj;
		return Objects.equals(authorityCode, other.authorityCode) 
			&& Objects.equals(userId.organizationCode, other.userId.organizationCode) 
			&& Objects.equals(userId.userId, other.userId.userId);
	}
	
}
