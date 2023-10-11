package com.like.system.menu.application.port.dto;

import java.time.LocalDateTime;

import com.like.system.menu.domain.MenuGroup;

import lombok.Builder;

@Builder
public record MenuGroupSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,	
		String clientAppUrl,			
		String organizationCode,
		String menuGroupCode,		
		String menuGroupName,
		String description
		) {
	
	public MenuGroup newMenuGroup() {
		MenuGroup entity = MenuGroup.builder()
								    .organizationCode(this.organizationCode)
								    .code(this.menuGroupCode)
								    .name(this.menuGroupName)
								    .description(this.description)						    
								    .build();
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;	
	}
	
	public void modifyMenuGroup(MenuGroup menuGroup) {
		menuGroup.modifyEntity(this.menuGroupName, this.description);
		
		menuGroup.setAppUrl(clientAppUrl);
	}
	
	public static MenuGroupSaveDTO toDTO(MenuGroup entity) {
		if (entity == null) return null;
		
		return MenuGroupSaveDTO.builder()
							.createdDt(entity.getCreatedDt())
							.createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
							.modifiedDt(entity.getModifiedDt())
							.modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())								
							.organizationCode(entity.getId().getOrganizationCode())
							//.menuGroupId(entity.getId())
							.menuGroupCode(entity.getId().getMenuGroupCode())
							.menuGroupName(entity.getName())
							.description(entity.getDescription())
							.build();
	}
}
