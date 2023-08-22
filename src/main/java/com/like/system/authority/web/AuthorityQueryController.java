package com.like.system.authority.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.authority.boundary.AuthorityDTO;
import com.like.system.authority.domain.Authority;
import com.like.system.authority.service.AuthorityQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class AuthorityQueryController {

	private AuthorityQueryService service;
	
	public AuthorityQueryController(AuthorityQueryService service) {
		this.service = service;				
	}
	
	@GetMapping("/api/system/authority")
	public ResponseEntity<?> getAuthorityList(AuthorityDTO.SearchAuthority dto) {				
		
		List<Authority> authorityList = service.getAuthorityList(dto);								 							
		
		return toList(authorityList, MessageUtil.getQueryMessage(authorityList.size()));
	}
}
