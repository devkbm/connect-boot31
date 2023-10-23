package com.like.system.login.adapter.in.web;

import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.util.SessionUtil;
import com.like.system.login.application.service.LoginService;
import com.like.system.login.domain.AuthenticationToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {		
		
	private LoginService service;
		 
	public LoginController(LoginService service) {		
		this.service = service;
	}
	/*	 
	@PostMapping("/api/system/user/login")
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpServletRequest request) {			
						         		 							                   
		//String ipAddress = WebRequestUtil.getIpAddress(request);
		//System.out.println("접속 IP주소: " + ipAddress);
		
		return service.login(dto, request);
	}	
	*/
	@GetMapping("/api/system/user/auth")
	public AuthenticationToken get(HttpServletRequest request, @RequestParam String organizationCode) {
		return service.getAuthenticationToken(organizationCode, SessionUtil.getUserId(), request);
	}			     
    
	@Transactional
	@GetMapping("/api/system/user/session")
	public String getSession(HttpSession session) {
		/*
		session.getAttributeNames().asIterator()
         		  .forEachRemaining(name -> log.info("session name={}, value={}",name, session.getAttribute(name) ));
		*/
		
		log.info("sessionId={}", session.getId());
		log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
		log.info("creationTime={}", new Date(session.getCreationTime()));
		log.info("lastAccessTjme={}",new Date(session.getLastAccessedTime()));
		log.info("isNew={}", session.isNew());
		return "세션 출력";
	}
}
