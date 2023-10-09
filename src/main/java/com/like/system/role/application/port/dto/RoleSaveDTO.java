package com.like.system.role.application.port.dto;

import com.like.system.role.domain.Role;

public record RoleSaveDTO(
		String clientAppUrl,			
		String id,
		String organizationCode,
		String roleCode,
		String description
		) {
	
	public Role newEntity() {
		Role entity = new Role(this.organizationCode, this.roleCode, this.description);
		//entity.setAppUrl(clientAppUrl);			
		
		return entity;
	}
	
	public void modifyEntity(Role authority) {			
		authority.modifyEntity(description);
		//authority.setAppUrl(clientAppUrl);
	}
}
