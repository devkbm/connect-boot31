package com.like.system.login.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.login.application.port.in.LoginUseCase;
import com.like.system.login.application.port.in.dto.LoginRequestDTO;

import com.like.system.login.domain.AuthenticationToken;

@RestController
public class LoginController2 {		
		
	private LoginUseCase useCase;
		 
	public LoginController2(LoginUseCase useCase) {		
		this.useCase = useCase;
	}
		 
	
	@PostMapping("/api/system/user/login")
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpServletRequest request) {			
						         		 							                   
		//String ipAddress = WebRequestUtil.getIpAddress(request);
		//System.out.println("접속 IP주소: " + ipAddress);
		
		return useCase.login(dto, request);
	}	
	
}
