package com.like.system.login.application.port.in;

import com.like.system.login.application.port.in.dto.LoginRequestDTO;
import com.like.system.login.domain.AuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginUseCase {
	AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request);
}
