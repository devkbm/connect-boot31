package com.like.system.biztypecode.domain;

public class BizCode {

	BizCodeId id;
	
	String codeName;
	
	Boolean useYn;
	
	Integer sequence;
	
	String comment;
	
	BizCodeType bizCodeType;
	
	public BizCode(BizCodeType bizCodeType
				  ,String code
	              ,String name            
			      ,String comment) {
		this.bizCodeType = bizCodeType;
				
	}
}
