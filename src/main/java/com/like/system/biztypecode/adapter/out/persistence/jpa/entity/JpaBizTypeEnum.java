package com.like.system.biztypecode.adapter.out.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.system.core.dto.HtmlSelectOptionable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum JpaBizTypeEnum implements HtmlSelectOptionable {
	HRM("인사관리"),
	GROUPWARE("그룹웨어"),
	SYSTEM("시스템")
	;

	private String name;
	
	private JpaBizTypeEnum(final String name) {
		this.name = name;
	}
	
	@Override
	public String getLabel() {
		return name;
	}

	@Override
	public String getValue() {
		return this.toString();
	}
}

