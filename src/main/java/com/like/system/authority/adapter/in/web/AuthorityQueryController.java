package com.like.system.authority.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.authority.application.port.in.AuthorityQueryPort;
import com.like.system.authority.domain.Authority;
import com.like.system.core.message.MessageUtil;

@RestController
public class AuthorityQueryController {

	private AuthorityQueryPort service;
	
	public AuthorityQueryController(AuthorityQueryPort service) {
		this.service = service;				
	}
	
	@GetMapping("/api/system/authority")
	public ResponseEntity<?> getAuthorityList(AuthorityDTO.SearchAuthority dto) {				
		
		List<Authority> authorityList = service.getAuthorityList(dto);								 							
		
		return toList(authorityList, MessageUtil.getQueryMessage(authorityList.size()));
	}
}
