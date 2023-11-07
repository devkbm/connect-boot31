package com.like.system.login.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.util.SessionUtil;
import com.like.system.core.web.util.WebRequestUtil;
import com.like.system.login.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.login.domain.AuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationTokenController {

	AuthenticationTokenSelectUseCase useCase;
	
	AuthenticationTokenController(AuthenticationTokenSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/user/auth")
	public AuthenticationToken get(HttpServletRequest request, @RequestParam String organizationCode) {
		
		return useCase.select(organizationCode, 
							  SessionUtil.getUserId(), 
							  request.getSession().getId(), 
							  WebRequestUtil.getIpAddress(request));
	}
}
