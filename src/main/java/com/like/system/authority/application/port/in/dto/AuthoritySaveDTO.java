package com.like.system.authority.application.port.in.dto;

import com.like.system.authority.domain.Authority;

public record AuthoritySaveDTO(
		String clientAppUrl,			
		String id,
		String organizationCode,
		String authorityCode,
		String description
		) {
	
	public Authority newEntity() {
		Authority entity = new Authority(this.organizationCode, this.authorityCode, this.description);
		//entity.setAppUrl(clientAppUrl);			
		
		return entity;
	}
	
	public void modifyEntity(Authority authority) {			
		authority.modifyEntity(description);
		//authority.setAppUrl(clientAppUrl);
	}
}
