package com.like.system.menu.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;

import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuType;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class MenuDTO {	
	
	public record Search(
			String organizationCode,
			@NotEmpty(message = "필수 입력 값입니다.")
			String menuGroupCode,
			String menuCode,
			String menuName
			) {
		private static final QMenu qType = QMenu.menu;
		
		public BooleanBuilder getBooleanBuilder() {																
			return new BooleanBuilder()
					.and(equalOrganizationCode(this.organizationCode))
					.and(equalMenuGroupCode(this.menuGroupCode))
		//			.and(likeMenuId(this.menuId))
					.and(likeMenuName(this.menuName));
		}

		private BooleanExpression equalOrganizationCode(String organizationCode) {					
			return QMenuGroup.menuGroup.id.organizationCode.eq(organizationCode);
		}
		
		private BooleanExpression equalMenuGroupCode(String menuGroupCode) {					
			return QMenuGroup.menuGroup.id.menuGroupCode.eq(menuGroupCode);
		}
		/*
		private BooleanExpression likeMenuId(String menuId) {
			return hasText(menuId) ? qType.id.like("%"+menuId+"%") : null;					
		}
		*/
		private BooleanExpression likeMenuName(String menuName) {
			return hasText(menuName) ? qType.name.like("%"+menuName+"%") : null;					
		}
	}	
	
	@Builder
	public static record FormMenu(
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
		
		public static FormMenu convert(Menu menu) {
			
			return FormMenu.builder()
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
	
}
