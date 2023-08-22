package com.like.system.menu.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MenuGroupId implements Serializable {

	private static final long serialVersionUID = 9113349756522741742L;

	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="MENU_GROUP_CD")
	String menuGroupCode;
	
	protected MenuGroupId() {}

	public MenuGroupId(String organizationCode, String menuGroupCode) {		
		this.organizationCode = organizationCode;
		this.menuGroupCode = menuGroupCode;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getMenuGroupCode() {
		return menuGroupCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(organizationCode, menuGroupCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuGroupId other = (MenuGroupId) obj;
		return Objects.equals(organizationCode, other.organizationCode)
			&& Objects.equals(menuGroupCode, other.menuGroupCode);
	}
	
	
}
