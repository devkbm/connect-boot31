package com.like.system.authority.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthority;
import com.like.system.authority.application.port.in.AuthorityQueryUseCase;
import com.like.system.authority.application.port.in.dto.AuthorityQueryRequestDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class AuthorityQueryController {

	private AuthorityQueryUseCase service;
	
	public AuthorityQueryController(AuthorityQueryUseCase service) {
		this.service = service;				
	}
	
	@GetMapping("/api/system/authority")
	public ResponseEntity<?> getAuthorityList(AuthorityQueryRequestDTO dto) {				
		
		List<JpaAuthority> authorityList = service.getAuthorityList(dto);								 							
		
		return toList(authorityList, MessageUtil.getQueryMessage(authorityList.size()));
	}
}
