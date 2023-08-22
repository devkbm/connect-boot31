package com.like.system.user.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemUserMenuGroupId implements Serializable {

	private static final long serialVersionUID = 6717800175971753894L;

	SystemUserId userId;
	
	@Column(name="MENU_GROUP_CD")
	String menuGroupCode;
	
	protected SystemUserMenuGroupId() {}
	
	public SystemUserMenuGroupId(String organizationCode, String userId, String menuGroupCode) {
		this.userId = new SystemUserId(organizationCode, userId);
		this.menuGroupCode = menuGroupCode;
	}

	public SystemUserId getUserId() {
		return userId;
	}

	public String getMenuGroupCode() {
		return menuGroupCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(menuGroupCode, userId.organizationCode, userId.userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemUserMenuGroupId other = (SystemUserMenuGroupId) obj;
		return Objects.equals(menuGroupCode, other.menuGroupCode) 
			&& Objects.equals(userId.organizationCode, other.userId.organizationCode)
			&& Objects.equals(userId.userId, other.userId.userId);
	}

	
	
	
}
