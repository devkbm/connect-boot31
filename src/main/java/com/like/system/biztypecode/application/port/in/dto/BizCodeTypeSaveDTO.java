package com.like.system.biztypecode.application.port.in.dto;

import lombok.Builder;

@Builder
public record BizCodeTypeSaveDTO(
		String clientAppUrl,
		String organizationCode,
		String typeId,			
		String typeName,			
		Integer sequence,
		String bizType,
		String comment
		) {

}
