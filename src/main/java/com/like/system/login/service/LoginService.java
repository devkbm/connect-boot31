package com.like.system.login.service;

import java.util.Collection;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.login.boundary.LoginRequestDTO;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;
import com.like.system.user.domain.SystemUserRepository;

@Transactional
@Service
public class LoginService {

	private AuthenticationManager authenticationManager;
	private SystemUserRepository userRepository;		 	
	
	public LoginService(AuthenticationManager authenticationManager
					   ,SystemUserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}
	
	public AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request) {
		String username = dto.getUsername();
		String password = dto.password();
		
		SystemUser user = userRepository.findById(new SystemUserId(dto.organizationCode(), dto.staffNo())).orElseThrow(EntityNotFoundException::new);
		
		authentication(dto.organizationCode(), dto.staffNo(), password, user.getAuthorities(), request);
		
		return AuthenticationToken.of(user, request);
	}
	
	public AuthenticationToken getAuthenticationToken(String organizationCode, String userId, HttpServletRequest request) {
		SystemUser user = userRepository.findById(new SystemUserId(organizationCode, userId)).orElseThrow(EntityNotFoundException::new);
		
		return AuthenticationToken.of(user, request);
	}
	
	private void authentication(String organizationCode, String username, String password, Collection<? extends GrantedAuthority> collection, HttpServletRequest request) {
		// 로그인 요청정보 SpringSecurityUserService에서 사용하기 위해 THREAD_LOCAL에 저장
		LoginRequestContext.setLoginRequest(new LoginRequestDTO(organizationCode, username, password));
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, collection);		
		token.setDetails(this.getAuthenticationToken(organizationCode, username, request));			
		
		Authentication authentication = authenticationManager.authenticate(token); 
							
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		// 로그인 요청정보 THREAD_LOCAL에서 제거
		LoginRequestContext.remove();		
	}
}
