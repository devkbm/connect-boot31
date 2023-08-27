package com.like.system.authority.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.authority.application.port.in.AuthoritySaveUseCase;
import com.like.system.authority.application.port.in.dto.AuthoritySaveDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class AuthoritySaveController {

	AuthoritySaveUseCase useCase;
	
	public AuthoritySaveController(AuthoritySaveUseCase useCase) {
		this.useCase= useCase;
	}
	
	@PostMapping("/api/system/authority")
	public ResponseEntity<?> saveAuthority(@RequestBody AuthoritySaveDTO dto) {			
		
		useCase.save(dto);					
																				 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
