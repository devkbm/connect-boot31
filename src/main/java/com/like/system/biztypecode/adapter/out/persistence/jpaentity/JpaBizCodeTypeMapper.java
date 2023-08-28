package com.like.system.biztypecode.adapter.out.persistence.jpaentity;

import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.biztypecode.domain.BizCodeType;

public class JpaBizCodeTypeMapper {

	public static BizCodeTypeSaveDTO toDTO(JpaBizCodeType entity) {
		if (entity == null) return null;
		
		return BizCodeTypeSaveDTO.builder()
								 .organizationCode(entity.getId().getOrganizationCode())
								 .typeId(entity.getId().getTypeId())
								 .typeName(entity.getName())
								 .sequence(entity.getSequence())
								 .bizType(entity.getBizType().toString())
								 .comment(entity.getComment())
								 .build();
	}
	
	public static JpaBizCodeType toJPAEntity(BizCodeType entity) {
		// String organizationCode, String typeId, String name, BizTypeEnum bizType, String comment
		return new JpaBizCodeType(entity.getId().getOrganizationCode()
								 ,entity.getId().getTypeId()
								 ,entity.getName()
								 ,BizTypeEnum.valueOf(entity.getBizType().toString())
								 ,entity.getComment());
	}
	
}
