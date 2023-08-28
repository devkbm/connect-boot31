package com.like.system.biztypecode.application.port.in.dto;

import com.like.system.biztypecode.domain.BizCodeType;
import com.like.system.biztypecode.domain.BizTypeEnum;

public class BizCodeTypeMapper {

	public static BizCodeType toEntity(BizCodeTypeSaveDTO dto) {
		return new BizCodeType(dto.organizationCode()
								 ,dto.typeId()
								 ,dto.typeName()
								 ,BizTypeEnum.valueOf(dto.bizType())
								 ,dto.comment());		
	}
	
	
}
