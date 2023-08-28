package com.like.system.biztypecode.application.port.in.dto;

import lombok.Builder;

@Builder
public record BizCodeSaveDTO(
		String clientAppUrl,
		String organizationCode,
		String typeId,
		String code,
		String codeName,
		Boolean useYn,
		Integer sequence,
		String comment
		) {

}
