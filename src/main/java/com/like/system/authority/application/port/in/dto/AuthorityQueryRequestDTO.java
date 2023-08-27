package com.like.system.authority.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorityQueryRequestDTO(
		@NotBlank(message="조직 코드를 선택해주세요.")
		String organizationCode,
		String authorityCode,
		String authorityId,
		String description
		) {	
}
