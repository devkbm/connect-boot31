package com.like.system.authority.domain;

import java.util.Objects;

public class AuthorityId {

	String organizationCode;
	
	String authorityCode;
	
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

	@Override
	public int hashCode() {
		return Objects.hash(authorityCode, organizationCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityId other = (AuthorityId) obj;
		return Objects.equals(authorityCode, other.authorityCode)
				&& Objects.equals(organizationCode, other.organizationCode);
	}
	
	
}
