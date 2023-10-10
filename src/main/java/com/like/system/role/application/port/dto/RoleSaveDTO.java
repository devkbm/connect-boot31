package com.like.system.role.application.port.dto;

import com.like.system.role.domain.Role;

public record RoleSaveDTO(
		String clientAppUrl,			
		String id,
		String organizationCode,
		String roleCode,
		String roleName,
		String description
		) {
	
	public Role newEntity() {
		Role entity = new Role(this.organizationCode, this.roleCode, this.roleName, this.description, null);
		
		//entity.setAppUrl(clientAppUrl);			
		
		return entity;
	}
	
	public void modifyEntity(Role authority) {			
		authority.modifyEntity(description);
		//authority.setAppUrl(clientAppUrl);
	}
}
