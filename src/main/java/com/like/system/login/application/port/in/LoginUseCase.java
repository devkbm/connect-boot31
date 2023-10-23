package com.like.system.login.application.port.in;

import com.like.system.login.domain.AuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginUseCase {
	AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request);
}
