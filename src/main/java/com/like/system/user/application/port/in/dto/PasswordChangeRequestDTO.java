package com.like.system.user.application.port.in.dto;

public record PasswordChangeRequestDTO(
		String organizationCode,
		String userId,
		String beforePassword,
		String afterPassword
		) {	
}