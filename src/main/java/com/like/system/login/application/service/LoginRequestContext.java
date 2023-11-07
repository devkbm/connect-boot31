package com.like.system.login.application.service;

import com.like.system.login.application.port.dto.LoginRequestDTO;
/**
 * 로그인시 기관정보(organizationCode)를 저장하기 위해 ThreadLocal 사용
 * SpringSecurityUserService에서 참조함
 */
public class LoginRequestContext {

	private static final ThreadLocal<LoginRequestDTO> LOGIN_THREAD_LOCAL = new ThreadLocal<>();
	
	public static void set(LoginRequestDTO request) {
		LOGIN_THREAD_LOCAL.set(request);
	}
	
	public static LoginRequestDTO get() {
		return LOGIN_THREAD_LOCAL.get();
	}
	
	public static void remove() {
		LOGIN_THREAD_LOCAL.remove();
	}
	
	
}
