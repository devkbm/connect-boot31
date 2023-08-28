package com.like.system.biztypecode.adapter.out.persistence.jpa.entity;

import com.like.system.biztypecode.application.port.in.dto.BizCodeSaveDTO;
import com.like.system.biztypecode.domain.BizCode;
import com.like.system.biztypecode.domain.BizCodeType;

public class JpaBizCodeMapper {

	public static BizCodeSaveDTO toDTO(JpaBizCode jpaEntity) {		
		
		return BizCodeSaveDTO.builder()
							 .organizationCode(jpaEntity.getId().getBizCodeTypeId().getOrganizationCode())
							 .typeId(jpaEntity.getId().getBizCodeTypeId().getTypeId())
							 .code(jpaEntity.getId().getCode())
							 .codeName(jpaEntity.getCodeName())
							 .useYn(jpaEntity.getUseYn())
							 .sequence(jpaEntity.getSequence())
							 .comment(jpaEntity.getComment())
							 .build();		
	}
	
	public static BizCode toDomainEntity(JpaBizCode jpaEntity, BizCodeType bizCodeType) {
		
		return BizCode.builder()
					  .bizCodeType(bizCodeType)
					  .code(jpaEntity.getId().getCode())
					  .useYn(jpaEntity.getUseYn())
					  .name(jpaEntity.getCodeName())
					  .sequence(jpaEntity.getSequence())
					  .comment(jpaEntity.getComment())
				      .build();
	}
	
	public static JpaBizCode toJPAEntity(BizCode entity, JpaBizCodeType jpaBizCodeType) {
		
		return JpaBizCode.builder()
						 .bizType(jpaBizCodeType)
						 .code(entity.getId().getCode())
						 .name(entity.getCodeName())
						 .useYn(entity.getUseYn())
						 .sequence(entity.getSequence())
						 .comment(entity.getComment())
						 .build();
	}
}
