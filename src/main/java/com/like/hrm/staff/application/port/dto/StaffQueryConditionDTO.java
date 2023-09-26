package com.like.hrm.staff.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.like.hrm.staff.domain.model.QStaff;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@JsonInclude(Include.NON_EMPTY)
public record StaffQueryConditionDTO(
		LocalDate referenceDate,
		String staffId,
		String name,
		String deptType,
		String deptCode,
		List<String> deptCodeList,
		String deptName,
		String jobType,
		String jobCode
		) {
	
	private static final QStaff qStaff = QStaff.staff;
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();					
		
		builder				
			.and(likeId(this.staffId))
			.and(likeName(this.name));				
		
		return builder;
	}
	
	private BooleanExpression likeId(String id) {
		return hasText(id) ? qStaff.id.staffNo.like("%"+id+"%") : null;					
	}
	
	private BooleanExpression likeName(String name) {
		return hasText(name) ? qStaff.name.name.like("%"+name+"%") : null;			
	}	
}