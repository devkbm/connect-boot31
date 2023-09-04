package com.like.system.menu.application.port.in.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.validation.constraints.NotEmpty;

public record MenuQueryConditionDTO(
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
