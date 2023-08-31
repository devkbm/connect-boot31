package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;
import com.like.system.user.application.port.in.dto.SystemUserDTO;
import com.like.system.user.application.service.SystemUserService;
import com.like.system.user.domain.SystemUser;

@RestController
public class SystemUserProfileController {		
				
	private SystemUserService userService;
		
	public SystemUserProfileController(SystemUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/api/system/user/my-profile")
	public ResponseEntity<?> getUserProfile(@RequestParam String organizationCode) throws FileNotFoundException, IOException {
														
		SystemUser user = userService.getUser(organizationCode, SessionUtil.getUserId());				
		
		SystemUserDTO.FormSystemUser dto = SystemUserDTO.FormSystemUser.convertDTO(user);					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}			
}
