package com.like.system.role.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.role.application.port.dto.RoleSaveDTO;
import com.like.system.role.application.port.in.RoleSaveUseCase;

@RestController
public class RoleSaveController {

	RoleSaveUseCase useCase;
	
	public RoleSaveController(RoleSaveUseCase useCase) {
		this.useCase= useCase;
	}
	
	@PostMapping("/api/system/role")
	public ResponseEntity<?> saveAuthority(@RequestBody RoleSaveDTO dto) {			
		
		useCase.save(dto);					
																				 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
