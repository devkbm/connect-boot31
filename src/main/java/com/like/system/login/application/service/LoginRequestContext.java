package com.like.system.login.application.service;

import com.like.system.login.application.port.in.dto.LoginRequestDTO;

public class LoginRequestContext {

	private static final ThreadLocal<LoginRequestDTO> LOGIN_THREAD_LOCAL = new ThreadLocal<>();
	
	public static void setLoginRequest(LoginRequestDTO request) {
		LOGIN_THREAD_LOCAL.set(request);
	}
	
	public static LoginRequestDTO getLoginRequest() {
		return LOGIN_THREAD_LOCAL.get();
	}
	
	public static void remove() {
		LOGIN_THREAD_LOCAL.remove();
	}
	
	
}
