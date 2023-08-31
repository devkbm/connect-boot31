package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;
import com.like.system.user.application.port.in.dto.PasswordChangeRequestDTO;
import com.like.system.user.application.port.in.dto.SystemUserDTO;
import com.like.system.user.application.service.SystemUserService;
import com.like.system.user.domain.SystemUser;

@RestController
public class SystemUserController {		
				
	private SystemUserService userService;
		
	public SystemUserController(SystemUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/api/system/user/my-profile")
	public ResponseEntity<?> getUserProfile(@RequestParam String organizationCode) throws FileNotFoundException, IOException {
														
		SystemUser user = userService.getUser(organizationCode, SessionUtil.getUserId());				
		
		SystemUserDTO.FormSystemUser dto = SystemUserDTO.FormSystemUser.convertDTO(user);					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}
	
	@GetMapping("/api/system/user/{userId}")
	public ResponseEntity<?> getUser(@RequestParam String organizationCode, @PathVariable String userId) throws FileNotFoundException, IOException {
						
		SystemUser user = userService.getUser(organizationCode, userId);				
		
		SystemUserDTO.FormSystemUser dto = SystemUserDTO.FormSystemUser.convertDTO(user);					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}		
	
	@PostMapping("/api/system/user")	
	public ResponseEntity<?> saveUser(@Valid @RequestBody SystemUserDTO.FormSystemUser dto) {			
											
		userService.saveUser(dto);					
																					 		
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
	
	@DeleteMapping("/api/system/user/{userId}")
	public ResponseEntity<?> deleteUser(@RequestParam String organizationCode, @PathVariable String userId) {
										
		userService.deleteUser(organizationCode, userId);															
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
		
	@PostMapping("/api/system/user/{id}/changepassword")
	public ResponseEntity<?> changePassword(@RequestParam String organizationCode,@RequestBody PasswordChangeRequestDTO dto) {				
						
		userService.changePassword(organizationCode, dto.userId(), dto.beforePassword(), dto.afterPassword());													
								 					
		return toList(null, "비밀번호가 변경되었습니다.");
	}
			
	@PostMapping("/api/system/user/{userId}/initpassword")
	public ResponseEntity<?> initializePassword(@RequestParam String organizationCode, @PathVariable String userId) {			
				
		userService.initPassword(organizationCode, userId);														
								 					
		return toList(null, "비밀번호가 초기화되었습니다.");
	}	
			
}
