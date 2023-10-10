package com.like.system.role.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
	
	RoleId id;
	
	String roleName;
		
	String description;	
	
	List<String> menuGroupCodeList;
	
	public Role(String organizationCode, String roleCode, String roleName, String description, List<String> menuGroupCodeList) {		
		this.id = new RoleId(organizationCode, roleCode);		
		this.roleName = roleName;
		this.description = description;
		this.menuGroupCodeList = menuGroupCodeList;
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
	}
	
	public void setMenuGroupCodeList(List<String> menuGroupCodeList) {
		this.menuGroupCodeList = menuGroupCodeList;
	}
	
	public void addMenuGroupCode(String menuGroupCode) {
		if (menuGroupCodeList == null) this.menuGroupCodeList = new ArrayList<>();
		
		this.menuGroupCodeList.add(menuGroupCode);
	}
		
	public String getOrganizationCode() {
		return this.id.getOrganizationCode();
	}

	public String getRoleCode() {
		return this.id.getRoleCode();
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public String getDescription() {
		return description;
	}
	
}
