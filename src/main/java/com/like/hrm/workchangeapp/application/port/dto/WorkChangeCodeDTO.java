package com.like.hrm.workchangeapp.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.hrm.workchangeapp.domain.QWorkChangeCode;
import com.like.hrm.workchangeapp.domain.WorkChangeCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class WorkChangeCodeDTO {

	public static class SearchDutyCode implements Serializable {
			
		private static final long serialVersionUID = 393877591925132395L;

		private final QWorkChangeCode qDutyCode = QWorkChangeCode.workChangeCode; 
		
		String dutyCode;
		
		String dutyName;
		
		Boolean enabled;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			builder
				.and(likeDutyCode(this.dutyCode))
				.and(likeDutyName(this.dutyName));
			
			return builder;
		}
		
		private BooleanExpression likeDutyCode(String dutyCode) {
			return hasText(dutyCode) ? qDutyCode.dutyCode.like("%"+dutyCode+"%") : null;					
		}
		
		private BooleanExpression likeDutyName(String dutyName) {
			return hasText(dutyName) ? qDutyCode.dutyName.like("%"+dutyName+"%") : null;					
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveDutyCode implements Serializable {				

		private static final long serialVersionUID = -5993523394655458647L;

		private String dutyCode;
				
		private String dutyName;
				
		private Boolean enabled;
				
		private String dutyGroup;
					
		private Boolean isFamilyEvent;
		
		private Long familyEventAmt;		
		
		private String comment;
		
		private List<Long> dutyApplicationInputLimitIdList;
		
		public WorkChangeCode newEntity() {
			return WorkChangeCode.builder()
						   .dutyCode(dutyCode)
						   .dutyName(dutyName)
						   .enabled(enabled)
						   .dutyGroup(dutyGroup)
						   .isFamilyEvent(isFamilyEvent)
						   .familyEventAmt(familyEventAmt)
						   .comment(comment)
						   .dutyApplicationInputLimitIdList(dutyApplicationInputLimitIdList)
						   .build();
		}
		
		public void modifyEntity(WorkChangeCode entity) {
			entity.modifyEntity(dutyName
							   ,enabled
							   ,dutyGroup
							   ,isFamilyEvent
							   ,familyEventAmt
							   ,comment);
		}
		
		public static SaveDutyCode convert(WorkChangeCode entity) {
			return SaveDutyCode.builder()
							   .dutyCode(entity.getDutyCode())
							   .dutyName(entity.getDutyName())
							   .enabled(entity.getEnabled())
							   .dutyGroup(entity.getDutyGroup())
							   .isFamilyEvent(entity.getIsFamilyEvent())
							   .familyEventAmt(entity.getFamilyEventAmt())
							   .comment(entity.getComment())
							   .dutyApplicationInputLimitIdList(entity.getDutyCodeRule().stream().map(e -> e.getDutyApplicationInputLimitId()).collect(Collectors.toList()))
							   .build();
		}
	}
}
