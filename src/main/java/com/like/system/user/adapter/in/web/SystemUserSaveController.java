package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.application.port.in.SystemUserSaveUseCase;
import com.like.system.user.application.port.in.dto.SystemUserDTO;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;
import com.like.system.user.application.service.SystemUserService;

@RestController
public class SystemUserSaveController {		
				
	private SystemUserService userService;
	SystemUserSaveUseCase userCase;
		
	public SystemUserSaveController(SystemUserService userService, SystemUserSaveUseCase userCase) {
		this.userService = userService;
		this.userCase = userCase;
	}	
	
	/*
	@PostMapping("/api/system/user")	
	public ResponseEntity<?> saveUser(@Valid @RequestBody SystemUserDTO.FormSystemUser dto) {			
											
		userService.saveUser(dto);					
																					 		
		return toList(null, MessageUtil.getSaveMessage(1));
	}			
	*/
	
	@PostMapping("/api/system/user")	
	public ResponseEntity<?> saveUser(@Valid @RequestBody SystemUserSaveDTO dto) {			
											
		userCase.save(dto);					
																					 		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
