package com.like.system.core.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {	
	
	@Override
	public void commence(
			final HttpServletRequest request, 
			final HttpServletResponse response,
			final AuthenticationException authException) throws IOException, ServletException {
								
		// 인증 없이 Request 수신시 "Unauthorized(401)" 응답
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());		
	}

}
