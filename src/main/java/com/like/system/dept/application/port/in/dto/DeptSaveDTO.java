package com.like.system.dept.application.port.in.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.lang.Nullable;

import com.like.system.core.jpa.vo.LocalDatePeriod;
import com.like.system.dept.domain.Dept;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record DeptSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,
		String parentDeptCode,					
		String organizationCode,
		@NotEmpty(message="부서코드는 필수 입력 사항입니다.")
		String deptCode,
		@NotEmpty(message="부서명(한글)은 필수 입력 사항입니다.")
		String deptNameKorean,
		String deptAbbreviationKorean,
		String deptNameEnglish,
		String deptAbbreviationEnglish,
		@DateTimeFormat(iso = ISO.DATE  )
		LocalDate fromDate,
		@DateTimeFormat(iso = ISO.DATE  )
		//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
		LocalDate toDate,
		Integer seq,
		String comment
		) {

	public static DeptSaveDTO toDTO(Dept entity) {							
		
		Optional<Dept> parent = Optional.ofNullable(entity.getParentDept());
		Optional<LocalDatePeriod> period= Optional.ofNullable(entity.getPeriod());
		
		DeptSaveDTO dto = DeptSaveDTO.builder()
							   .createdDt(entity.getCreatedDt())
							   .createdBy(entity.getCreatedBy().getLoggedUser())
							   .modifiedDt(entity.getModifiedDt())
							   .modifiedBy(entity.getModifiedBy().getLoggedUser())								  
							   .organizationCode(entity.getId().getOrganizationCode())
							   .deptCode(entity.getId().getDeptCode())
							   .parentDeptCode(parent.map(r -> r.getId().getDeptCode()).orElse(null))
							   .deptNameKorean(entity.getDeptNameKorean())
							   .deptAbbreviationKorean(entity.getDeptAbbreviationKorean())
							   .deptNameEnglish(entity.getDeptNameEnglish())
							   .deptAbbreviationEnglish(entity.getDeptAbbreviationEnglish())
							   .fromDate(period.map(LocalDatePeriod::getFrom).orElse(null))
							   .toDate(period.map(LocalDatePeriod::getTo).orElse(null))
							   .seq(entity.getSeq())
							   .comment(entity.getComment())
							   .build();		
		return dto;		
	}	
	
	public Dept toEntity(@Nullable Dept parentDept) {
		if (this.organizationCode == null) new IllegalArgumentException("조직코드가 없습니다.");
		if (this.deptCode == null) new IllegalArgumentException("부서코드가 없습니다.");
		
		Dept entity = Dept.builder(this.organizationCode,this.deptCode)									   					  
						   .deptNameKorean(this.deptNameKorean)
						   .deptAbbreviationKorean(this.deptAbbreviationKorean)
						   .deptNameEnglish(this.deptNameEnglish)
						   .deptAbbreviationEnglish(this.deptAbbreviationEnglish)
						   .period(new LocalDatePeriod(this.fromDate, this.toDate))					   
						   .seq(this.seq)
						   .comment(this.comment)
						   .parentDept(parentDept)
						   .parentDeptCode(parentDept.getId().getDeptCode())
						   .build();
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	/*
	public void modifyDept(Dept dept, @Nullable Dept parentDept) {
		dept.modifyEntity(deptNameKorean
						 ,deptAbbreviationKorean
						 ,deptNameEnglish
						 ,deptAbbreviationEnglish
						 ,new LocalDatePeriod(this.fromDate, this.toDate)
						 ,seq
						 ,comment
						 ,parentDept);
		
		dept.setAppUrl(clientAppUrl);
	}
	*/
}
