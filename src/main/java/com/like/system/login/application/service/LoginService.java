package com.like.system.login.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.like.system.login.application.port.dto.LoginRequestDTO;
import com.like.system.login.application.port.in.LoginUseCase;
import com.like.system.login.application.port.out.AuthenticationTokenSavePort;
import com.like.system.login.application.port.out.SystemUserSelectDbPort;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.login.domain.event.LoginSuccessEvent;
import com.like.system.menu.application.port.in.SystemUserMenuGroupSelectUseCase;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class LoginService implements LoginUseCase {

	SystemUserSelectDbPort userPort;
	SystemUserMenuGroupSelectUseCase menuGroupSelectUseCase;
	AuthenticationTokenSavePort	tokenPort;
	
	ApplicationEventPublisher publisher;
	
	public LoginService(SystemUserSelectDbPort userPort
						,SystemUserMenuGroupSelectUseCase menuGroupSelectUseCase
						,AuthenticationTokenSavePort tokenPort
						,ApplicationEventPublisher publisher) {
		this.userPort = userPort;
		this.menuGroupSelectUseCase = menuGroupSelectUseCase;
		this.tokenPort = tokenPort;
		this.publisher = publisher;
	}
	
	@Override
	public AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request) {
		String organizationCode = dto.organizationCode();
		String staffNo = dto.staffNo();
		String password = dto.password();
		
		// 로그인 요청정보 SpringSecurityUserService에서 사용하기 위해 THREAD_LOCAL에 저장
		LoginRequestContext.set(new LoginRequestDTO(organizationCode, staffNo, password));
		
		SystemUser systemUser = userPort.select(organizationCode, staffNo);
		List<MenuGroupSaveDTO> menuGroupList = menuGroupSelectUseCase.select(organizationCode, staffNo);
		AuthenticationToken token = tokenPort.SaveAuthenticationToken(dto, systemUser, menuGroupList, request);
					
		// 로그인 요청정보 THREAD_LOCAL에서 제거
		LoginRequestContext.remove();

		this.publisher.publishEvent(new LoginSuccessEvent(organizationCode, staffNo, LocalDate.now(), "login"));
		
		return token;
	}

}
