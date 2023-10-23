package com.like.system.login.application.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.core.web.util.WebRequestUtil;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.menu.application.port.in.SystemUserMenuGroupSelectUseCase;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Transactional
@Service
public class LoginService {

	private AuthenticationManager authenticationManager;
	private SystemUserRepository userRepository;	
	SystemUserMenuGroupSelectUseCase menuGroupSelectUseCase;
	
	public LoginService(AuthenticationManager authenticationManager
					   ,SystemUserRepository userRepository
					   ,SystemUserMenuGroupSelectUseCase menuGroupSelectUseCase) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.menuGroupSelectUseCase = menuGroupSelectUseCase;
	}
	/*
	public AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request) {
		String organizationCode = dto.organizationCode();
		String staffNo = dto.staffNo();
		String password = dto.password();
		
		// 로그인 요청정보 SpringSecurityUserService에서 사용하기 위해 THREAD_LOCAL에 저장
		LoginRequestContext.setLoginRequest(new LoginRequestDTO(organizationCode, staffNo, password));
		
		SystemUser user = userRepository.findById(new SystemUserId(organizationCode, staffNo)).orElseThrow(EntityNotFoundException::new);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(staffNo, password, user.getAuthorities());		
		token.setDetails(this.getAuthenticationToken(organizationCode, staffNo, request));
		
		Authentication authentication = authenticationManager.authenticate(token); 
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		this.setSessionSecurityContext(request, SecurityContextHolder.getContext());
		
		// 로그인 요청정보 THREAD_LOCAL에서 제거
		LoginRequestContext.remove();
						
		return AuthenticationToken.of(user, request);
	}	
	*/
	private void setSessionSecurityContext(HttpServletRequest request, SecurityContext context) {
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
	}
	
	public AuthenticationToken getAuthenticationToken(String organizationCode, String userId, HttpServletRequest request) {
		SystemUser user = userRepository.findById(new SystemUserId(organizationCode, userId)).orElseThrow(EntityNotFoundException::new);
		
		List<MenuGroupSaveDTO> menuGroupList = menuGroupSelectUseCase.select(organizationCode, user.getStaffId().getStaffNo());
		
		return AuthenticationToken.of(user, menuGroupList, WebRequestUtil.getIpAddress(request), request.getSession().getId());
	}
	
}
