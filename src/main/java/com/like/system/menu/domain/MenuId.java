package com.like.system.menu.domain;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

	@Override
	public int hashCode() {
		return Objects.hash(menuCode, menuGroupId.organizationCode, menuGroupId.menuGroupCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuId other = (MenuId) obj;
		return Objects.equals(menuCode, other.menuCode) 
			&& Objects.equals(menuGroupId.organizationCode, other.menuGroupId.organizationCode)
			&& Objects.equals(menuGroupId.menuGroupCode, other.menuGroupId.menuGroupCode);
	}
	
	
}
