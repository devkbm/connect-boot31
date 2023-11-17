package com.like.system.permission.application.port.in;

import com.like.system.permission.domain.AuthenticationToken;

public interface AuthenticationTokenSelectUseCase {
	AuthenticationToken select(String organizationCode, String userId, String sessionId, String ipAddress);
}
