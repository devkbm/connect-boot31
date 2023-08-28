package com.like.system.biztypecode.domain;

public enum BizTypeEnum {

	HRM("인사관리"),
	GROUPWARE("그룹웨어"),
	SYSTEM("시스템")
	;

	private String name;
	
	private BizTypeEnum(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
