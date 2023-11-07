package com.like.system.login.application.port.in;

import com.like.system.login.domain.AuthenticationToken;

public interface AuthenticationTokenSelectUseCase {
	AuthenticationToken select(String organizationCode, String userId, String sessionId, String ipAddress);
}
