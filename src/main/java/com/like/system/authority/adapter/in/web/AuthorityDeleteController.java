package com.like.system.authority.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.authority.application.port.in.AuthorityDeleteUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class AuthorityDeleteController {

	AuthorityDeleteUseCase useCase;
	
	public AuthorityDeleteController(AuthorityDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/system/authority/{authorityId}")
	public ResponseEntity<?> deleteAuthority(@RequestParam String organizationCode, @PathVariable String authorityId) {
		
		useCase.delete(organizationCode, authorityId);					
			
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
