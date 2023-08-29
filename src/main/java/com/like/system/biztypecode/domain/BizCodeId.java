package com.like.system.biztypecode.domain;

import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public class BizCodeId {

	BizCodeTypeId bizCodeTypeId;
	
	String code;

	public BizCodeId(String organizationCode, String typeId, String code) {		
		if (!StringUtils.hasText(code)) throw new IllegalArgumentException("code는 필수 입력 값입니다.");
		
		this.bizCodeTypeId = new BizCodeTypeId(organizationCode, typeId);		
		this.code = code;
	}
	
}
