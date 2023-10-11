package com.like.system.menu.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MenuRoleMappingId {

	MenuId menuId;
	
	@Comment("메뉴코드")
	@Column(name="ROLE_CD")
	String roleCode;
	
	protected MenuRoleMappingId() {}

	public MenuRoleMappingId(String organizationCode, String menuGroupCode, String menuCode, String roleCode) {	
		this.menuId = new MenuId(organizationCode, menuGroupCode, menuCode);
		this.roleCode = roleCode;
	}
}
