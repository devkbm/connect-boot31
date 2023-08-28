package com.like.system.biztypecode.application.port.in.dto;

import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCode;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeType;

import lombok.AccessLevel;
import lombok.Builder;

public class BizCodeDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,
			String typeId,
			String code,
			String codeName,
			Boolean useYn,
			Integer sequence,
			String comment
			) {
		
		public static Form convert(JpaBizCode entity) {			
			if (entity == null) return null;
			
			return Form.builder()
					   .typeId(entity.getId().getBizCodeTypeId().getTypeId())
					   .code(entity.getId().getCode())
					   .codeName(entity.getCodeName())
					   .useYn(entity.getUseYn())
					   .sequence(entity.getSequence())
					   .comment(entity.getComment())
					   .build();			
		}
		
		public JpaBizCode newEntity(JpaBizCodeType bizType) {			
			
			JpaBizCode entity = new JpaBizCode(bizType, code, codeName, useYn, sequence, comment); 
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public JpaBizCode modify(JpaBizCode entity) {
			
			entity.modify(codeName
						 ,useYn
						 ,sequence						 
						 ,comment);
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}		
	}
	
	
}
