package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.user.application.port.in.dto.PasswordChangeRequestDTO;
import com.like.system.user.application.service.SystemUserService;

@RestController
public class SystemUserPasswordChangeController {		
				
	private SystemUserService userService;
		
	public SystemUserPasswordChangeController(SystemUserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/api/system/user/{id}/changepassword")
	public ResponseEntity<?> changePassword(@RequestParam String organizationCode,@RequestBody PasswordChangeRequestDTO dto) {				
						
		userService.changePassword(organizationCode, dto.userId(), dto.beforePassword(), dto.afterPassword());													
								 					
		return toList(null, "비밀번호가 변경되었습니다.");
	}			
			
}
