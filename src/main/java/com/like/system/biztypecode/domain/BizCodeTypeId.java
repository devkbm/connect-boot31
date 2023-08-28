package com.like.system.biztypecode.domain;

import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public class BizCodeTypeId {

	String organizationCode;
	
	String typeId;
	
	public BizCodeTypeId(String organizationCode, String typeId) {
		if (!StringUtils.hasText(organizationCode)) throw new IllegalArgumentException("ID는 필수 입력 값입니다.");
		if (!StringUtils.hasText(typeId)) throw new IllegalArgumentException("ID는 필수 입력 값입니다.");
		
		this.organizationCode = organizationCode;
		this.typeId = typeId;
	}
}
