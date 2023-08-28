package com.like.system.biztypecode.domain;

import lombok.Getter;

@Getter
public class BizCodeId {

	String organizationCode;
	
	String typeId;
	
	String code;

	public BizCodeId(String organizationCode, String typeId, String code) {		
		this.organizationCode = organizationCode;
		this.typeId = typeId;
		this.code = code;
	}
	
}
