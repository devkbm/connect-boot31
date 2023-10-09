package com.like.system.role.domain;

public class Role {
	
	RoleId id;
		
	String description;	
	
	public Role(String organizationCode, String authorityCode, String description) {		
		this.id = new RoleId(organizationCode, authorityCode);
		this.description = description;
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
	}		
	
	public String getOrganizationCode() {
		return this.id.getOrganizationCode();
	}

	public String getAuthorityCode() {
		return this.id.getAuthorityCode();
	}
	
	public String getDescription() {
		return description;
	}
	
}
