package com.like.system.dept.application.port.in.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.system.core.jpa.vo.LocalDatePeriod;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptHierarchyResponse {
	
	String parentDeptCode;
	
	String organizationCode;
	
	//String deptId;
	
	String deptCode;		
			
	String deptNameKorean;		
	
	String deptAbbreviationKorean;
	
	String deptNameEnglish;
	
	String deptAbbreviationEnglish;
						
	LocalDate fromDate;
			
	LocalDate toDate;
	
	Integer seq;
	
	String comment;
	
	@Singular
	List<DeptHierarchyResponse> children;
	
	/**
	 * NzTreeNode property 
	 */
	String title;
	
	String key;
			
	@JsonProperty(value="isLeaf") 
	boolean isLeaf;

	@QueryProjection
	public DeptHierarchyResponse(String parentDeptCode, String organizationCode, String deptCode, String deptNameKorean, String deptAbbreviationKorean,
			String deptNameEnglish, String deptAbbreviationEnglish, LocalDatePeriod period,
			Integer seq, String comment) {				
		this.parentDeptCode = parentDeptCode;
		this.organizationCode = organizationCode;		
		this.deptCode = deptCode;
		this.deptNameKorean = deptNameKorean;
		this.deptAbbreviationKorean = deptAbbreviationKorean;
		this.deptNameEnglish = deptNameEnglish;
		this.deptAbbreviationEnglish = deptAbbreviationEnglish;
		this.fromDate = period.getFrom();
		this.toDate = period.getTo();
		this.seq = seq;
		this.comment = comment;
		
		this.title 	= this.deptNameKorean;
		this.key 	= this.deptCode;			
	}

	public void setChildren(List<DeptHierarchyResponse> children) {
		this.children = children;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
