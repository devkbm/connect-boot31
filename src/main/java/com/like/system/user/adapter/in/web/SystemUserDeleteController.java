package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.application.port.in.SystemUserDeleteUseCase;

@RestController
public class SystemUserDeleteController {		
				
	private SystemUserDeleteUseCase useCase;
		
	public SystemUserDeleteController(SystemUserDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/system/user/{userId}")
	public ResponseEntity<?> deleteUser(@RequestParam String organizationCode, @PathVariable String userId) {
										
		useCase.delete(organizationCode, userId);															
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}		
			
}
