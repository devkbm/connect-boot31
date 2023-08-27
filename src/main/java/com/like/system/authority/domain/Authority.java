package com.like.system.authority.domain;

public class Authority {
	
	AuthorityId id;
		
	String description;	
	
	public Authority(String organizationCode, String authorityCode, String description) {		
		this.id = new AuthorityId(organizationCode, authorityCode);
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
