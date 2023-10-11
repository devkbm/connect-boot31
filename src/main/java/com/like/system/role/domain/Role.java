package com.like.system.role.domain;

public class Role {
	
	RoleId id;
	
	String roleName;
		
	String description;		
	
	public Role(String organizationCode, String roleCode, String roleName, String description) {		
		this.id = new RoleId(organizationCode, roleCode);		
		this.roleName = roleName;
		this.description = description;		
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
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
