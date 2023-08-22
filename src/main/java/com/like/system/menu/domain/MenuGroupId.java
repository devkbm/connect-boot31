package com.like.system.menu.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
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
	
	
}
