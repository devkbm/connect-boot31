package com.like.system.authority.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import com.like.system.authority.application.port.in.AuthoritySelectUseCase;
import com.like.system.authority.domain.Authority;
import com.like.system.core.message.MessageUtil;

@RestController
public class AuthoritySelectController {

	private AuthoritySelectUseCase useCase;
	
	public AuthoritySelectController(AuthoritySelectUseCase useCase) {
		this.useCase = useCase;
	}		
	
	@GetMapping("/api/system/authority/{authorityId}")
	public ResponseEntity<?> getAuthority(@RequestParam String organizationCode, @PathVariable String authorityId) {			
		
		Authority authority = useCase.select(organizationCode, authorityId);										
		
		return toOne(authority, MessageUtil.getQueryMessage(authority == null ? 0 : 1));
	}
				
	
	
}
