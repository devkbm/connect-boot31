package com.like.system.authority.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;
import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import com.like.system.authority.boundary.AuthorityDTO;
import com.like.system.authority.domain.Authority;
import com.like.system.authority.service.AuthorityService;
import com.like.system.core.message.MessageUtil;

@RestController
public class AuthorityController {

	private AuthorityService service;
	
	public AuthorityController(AuthorityService service) {
		this.service = service;
	}		
	
	@GetMapping("/api/system/authority/{authorityId}")
	public ResponseEntity<?> getAuthority(@RequestParam String organizationCode, @PathVariable String authorityId) {			
		
		Authority authority = service.getAuthority(organizationCode, authorityId);										
		
		return toOne(authority, MessageUtil.getQueryMessage(authority == null ? 0 : 1));
	}
		
	@PostMapping("/api/system/authority")
	public ResponseEntity<?> saveAuthority(@RequestBody AuthorityDTO.FormAuthority dto) {			
		
		service.createAuthority(dto);					
																				 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
	
	@DeleteMapping("/api/system/authority/{authorityId}")
	public ResponseEntity<?> deleteAuthority(@RequestParam String organizationCode, @PathVariable String authorityId) {
		
		service.deleteAuthority(organizationCode, authorityId);					
			
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
