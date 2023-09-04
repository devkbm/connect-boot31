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
	
	public static MenuSaveDTO toDTO(Menu menu) {
		
		return MenuSaveDTO.builder()
				   	   .createdDt(menu.getCreatedDt())
				   	   .createdBy(menu.getCreatedBy().getLoggedUser())
				   	   .modifiedDt(menu.getModifiedDt())
				   	   .modifiedBy(menu.getModifiedBy().getLoggedUser())					   	   
				   	   .organizationCode(menu.getMenuGroup().getId().getOrganizationCode())
				   	   .menuGroupCode(menu.getMenuGroup().getId().getMenuGroupCode())					   	   					   	   
				   	   .menuCode(menu.getId().getMenuCode())
				   	   .menuName(menu.getName())
				   	   .menuType(menu.getType().toString())
				   	   .appUrl(menu.getAppUrl())
				   	   .sequence(menu.getSequence())
				   	   .level(menu.getLevel())
				   	   .parentMenuCode(menu.getParentMenuCode())					   	   
				   	   .build();
	}
}
