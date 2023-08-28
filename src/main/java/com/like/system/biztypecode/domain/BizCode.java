package com.like.system.biztypecode.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BizCode {

	BizCodeId id;
	
	String codeName;
	
	Boolean useYn;
	
	Integer sequence;
	
	String comment;
	
	BizCodeType bizCodeType;
	
	@Builder
	public BizCode(BizCodeType bizCodeType
				  ,String code
				  ,Boolean useYn
	              ,String name
	              ,Integer sequence
			      ,String comment) {
		this.bizCodeType = bizCodeType;
		this.id = new BizCodeId(bizCodeType.getId().getOrganizationCode()
							   ,bizCodeType.getId().getTypeId()
							   ,code);		
		this.codeName = name;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;				
	}
		
}
