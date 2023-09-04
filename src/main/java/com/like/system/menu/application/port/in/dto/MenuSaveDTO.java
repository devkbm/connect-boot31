package com.like.system.menu.application.port.in.dto;

import java.time.LocalDateTime;

import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuType;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record MenuSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,						
		String organizationCode,
		@NotEmpty
		String menuGroupCode,
		String menuCode,			
		@NotEmpty
		String menuName,
		String appUrl,
		String parentMenuCode,
		String menuType,
		long sequence,
		long level
		) {
	public Menu newMenu(MenuGroup menuGroup) {
		Menu entity = Menu.builder()
						  .menuGroup(menuGroup)
						  .organizationCode(organizationCode)
						  .menuCode(this.menuCode)
						  .menuName(this.menuName)
						  .menuType(MenuType.valueOf(this.menuType))
						  .sequence(this.sequence)
						  .level(this.level)					   
						  .appUrl(this.appUrl)
						  .build();
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public void modifyMenu(Menu menu, Menu parentMenu, MenuGroup menuGroup) {
		menu.modifyEntity(this.menuName
				         ,MenuType.valueOf(this.menuType)
				         ,this.appUrl
				         ,this.sequence
				         ,this.level
				         ,parentMenu
				         ,menuGroup);
		
		menu.setAppUrl(clientAppUrl);			
	}
	
	public static MenuSaveDTO toDTO(Menu entity) {
		
		return MenuSaveDTO.builder()
				   	   .createdDt(entity.getCreatedDt())
				   	   .createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
				   	   .modifiedDt(entity.getModifiedDt())
				   	   .modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())					   	   
				   	   .organizationCode(entity.getMenuGroup().getId().getOrganizationCode())
				   	   .menuGroupCode(entity.getMenuGroup().getId().getMenuGroupCode())					   	   					   	   
				   	   .menuCode(entity.getId().getMenuCode())
				   	   .menuName(entity.getName())
				   	   .menuType(entity.getType().toString())
				   	   .appUrl(entity.getAppUrl())
				   	   .sequence(entity.getSequence())
				   	   .level(entity.getLevel())
				   	   .parentMenuCode(entity.getParentMenuCode())					   	   
				   	   .build();
	}
}
