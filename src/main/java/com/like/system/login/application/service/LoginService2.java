package com.like.system.login.application.service;

import org.springframework.stereotype.Service;

import com.like.system.login.application.port.in.LoginUseCase;
import com.like.system.login.application.port.in.dto.LoginRequestDTO;
import com.like.system.login.application.port.out.AuthenticationTokenSavePort;
import com.like.system.login.application.port.out.SystemUserSelectPort;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class LoginService2 implements LoginUseCase {

	SystemUserSelectPort userPort;
	AuthenticationTokenSavePort	tokenPort;
	
	public LoginService2(SystemUserSelectPort userPort
						,AuthenticationTokenSavePort tokenPort) {
		this.userPort = userPort;
		this.tokenPort = tokenPort;
	}
	@Override
	public AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request) {
		String organizationCode = dto.organizationCode();
		String staffNo = dto.staffNo();
		String password = dto.password();
		
		// 로그인 요청정보 SpringSecurityUserService에서 사용하기 위해 THREAD_LOCAL에 저장
		LoginRequestContext.setLoginRequest(new LoginRequestDTO(organizationCode, staffNo, password));
		
		SystemUser systemUser = userPort.select(organizationCode, staffNo);
		
		AuthenticationToken token = tokenPort.SaveAuthenticationToken(dto, systemUser, request);
		
		// 로그인 요청정보 THREAD_LOCAL에서 제거
		LoginRequestContext.remove();
		
		return token;
	}

}
