package com.like.system.role.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.role.application.port.in.RoleDeleteUseCase;

@RestController
public class RoleDeleteController {

	RoleDeleteUseCase useCase;
	
	public RoleDeleteController(RoleDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/system/role/{roleId}")
	public ResponseEntity<?> deleteAuthority(@RequestParam String organizationCode, @PathVariable String roleId) {
		
		useCase.delete(organizationCode, roleId);					
			
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
