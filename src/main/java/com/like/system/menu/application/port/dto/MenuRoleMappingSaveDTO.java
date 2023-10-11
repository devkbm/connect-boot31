package com.like.system.menu.application.port.dto;

import java.time.LocalDateTime;

import com.like.system.menu.domain.MenuRoleMapping;
import com.like.system.menu.domain.MenuRoleMappingId;

import lombok.Builder;

@Builder
public record MenuRoleMappingSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,	
		String organizationCode,
		String menuGroupCode,
		String menuCode,
		String roleCode
		) {

	public MenuRoleMapping toEntity() {
		MenuRoleMapping entity = new MenuRoleMapping(new MenuRoleMappingId(organizationCode, menuGroupCode, menuCode, roleCode));
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public static MenuRoleMappingSaveDTO toDTO(MenuRoleMapping entity) {
		return null;
	}
}
