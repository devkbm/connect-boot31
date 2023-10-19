package com.like.system.menu.domain;

import java.io.Serializable;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class MenuRoleMappingId implements Serializable {
	
	private static final long serialVersionUID = -128283423937776316L;

	MenuId menuId;
	
	@Comment("메뉴코드")
	@Column(name="ROLE_CD")
	String roleCode;
	
	protected MenuRoleMappingId() {}

	public MenuRoleMappingId(MenuId menuId, String roleCode) {	
		this.menuId = menuId;
		this.roleCode = roleCode;
	}
	
	public MenuRoleMappingId(String organizationCode, String menuGroupCode, String menuCode, String roleCode) {	
		this.menuId = new MenuId(organizationCode, menuGroupCode, menuCode);
		this.roleCode = roleCode;
	}
}
