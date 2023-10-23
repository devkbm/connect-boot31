package com.like.system.user.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.user.domain.QSystemUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.validation.constraints.NotBlank;

public record SystemUserQueryDTO(
		@NotBlank(message="조직 코드를 선택해주세요.")
		String organizationCode,
		String staffNo,			
		String name,
		String deptCode
		) {
	private static final QSystemUser qType = QSystemUser.systemUser;
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(eqOrganizationCode(this.organizationCode))
			   .and(likeStaffNo(this.staffNo))
			   .and(likeUserName(this.name))
		 	   .and(equalDeptCode(this.deptCode));						
		
		return builder;
	}
	
	private BooleanExpression eqOrganizationCode(String organizationCode) {
		return qType.staffId.organizationCode.eq(organizationCode);
	}
			
	private BooleanExpression likeStaffNo(String staffNo) {
		return hasText(staffNo) ? qType.staffId.staffNo.like("%"+staffNo+"%") : null;					
	}
	
	private BooleanExpression likeUserName(String name) {
		return hasText(name) ? qType.name.like("%"+name+"%") : null;					
	}
	
	private BooleanExpression equalDeptCode(String deptCode) {
		return hasText(deptCode) ? qType.dept.id.deptCode.eq(deptCode) : null;					
	}
}
