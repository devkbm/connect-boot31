package com.like.system.login.application.port.out;

import com.like.system.login.application.port.in.dto.LoginRequestDTO;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationTokenSavePort {
	AuthenticationToken SaveAuthenticationToken(LoginRequestDTO dto, SystemUser systemUser, HttpServletRequest request);
}
