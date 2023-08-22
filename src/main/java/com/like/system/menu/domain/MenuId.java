package com.like.system.menu.domain;

import java.io.Serializable;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class MenuId implements Serializable {

	private static final long serialVersionUID = 1466162239881162136L;

	MenuGroupId menuGroupId;
	
	@Comment("메뉴코드")
	@Column(name="MENU_CD")
	String menuCode;
	
	protected MenuId() {}

	public MenuId(String organizationCode, String menuGroupCode, String menuCode) {	
		this.menuGroupId = new MenuGroupId(organizationCode, menuGroupCode);
		this.menuCode = menuCode;
	}

	public MenuGroupId getMenuGroupId() {
		return menuGroupId;
	}

	public String getMenuCode() {
		return menuCode;
	}
	
	
}
