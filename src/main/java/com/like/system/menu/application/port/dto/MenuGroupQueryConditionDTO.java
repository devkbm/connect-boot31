package com.like.system.menu.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.menu.domain.QMenuGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.validation.constraints.NotBlank;

public record MenuGroupQueryConditionDTO(
		@NotBlank(message="조직 코드를 선택해주세요.")
		String organizationCode,
		String menuGroupId,
		String menuGroupName
		) {
	private static final QMenuGroup qType = QMenuGroup.menuGroup;
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder
			.and(eqOrganizationCode(this.organizationCode))
			.and(likeMenGroupId(this.menuGroupId))
			.and(likeMenGroupName(this.menuGroupName));
										
		return builder;		
	}
	
	private BooleanExpression eqOrganizationCode(String organizationCode) {
		return qType.id.organizationCode.eq(organizationCode);
	}
	
	private BooleanExpression likeMenGroupId(String menuGroupId) {
		return hasText(menuGroupId) ? qType.id.menuGroupCode.like("%"+menuGroupId+"%") : null;					
	}
	
	private BooleanExpression likeMenGroupName(String menuGroupName) {
		return hasText(menuGroupName) ? qType.name.like("%"+menuGroupName+"%") : null;			
	}
}
