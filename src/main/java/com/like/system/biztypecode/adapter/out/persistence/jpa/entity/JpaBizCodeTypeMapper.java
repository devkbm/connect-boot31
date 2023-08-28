package com.like.system.biztypecode.adapter.out.persistence.jpa.entity;

import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.biztypecode.domain.BizCodeType;
import com.like.system.biztypecode.domain.BizTypeEnum;

public class JpaBizCodeTypeMapper {

	public static BizCodeTypeSaveDTO toDTO(JpaBizCodeType jpaEntity) {
		if (jpaEntity == null) return null;
		
		return BizCodeTypeSaveDTO.builder()
								 .organizationCode(jpaEntity.getId().getOrganizationCode())
								 .typeId(jpaEntity.getId().getTypeId())
								 .typeName(jpaEntity.getName())
								 .sequence(jpaEntity.getSequence())
								 .bizType(jpaEntity.getBizType().toString())
								 .comment(jpaEntity.getComment())
								 .build();
	}
	
	public static BizCodeType toDomainEntity(JpaBizCodeType jpaEntity) {
		if (jpaEntity == null) return null;
							
		return BizCodeType.builder()
						  .organizationCode(jpaEntity.getId().getOrganizationCode())
						  .typeId(jpaEntity.getId().getTypeId())
						  .name(jpaEntity.getName())
						  .bizTypeEnum(BizTypeEnum.valueOf(jpaEntity.getBizType().toString()))
						  .comment(jpaEntity.getComment())
						  .build();
	}
	
	public static JpaBizCodeType toJPAEntity(BizCodeType entity) {
		if (entity == null) return null;
		
		return JpaBizCodeType.builder()
							 .organizationCode(entity.getId().getOrganizationCode())
							 .typeId(entity.getId().getTypeId())
							 .name(entity.getName())
							 .bizType(JpaBizTypeEnum.valueOf(entity.getBizType().toString()))
							 .comment(entity.getComment())
							 .build();				
	}
	
}
