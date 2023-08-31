package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.user.application.service.SystemUserService;

@RestController
public class SystemUserPasswordInitController {		
				
	private SystemUserService userService;
		
	public SystemUserPasswordInitController(SystemUserService userService) {
		this.userService = userService;
	}
			
	@PostMapping("/api/system/user/{userId}/initpassword")
	public ResponseEntity<?> initializePassword(@RequestParam String organizationCode, @PathVariable String userId) {			
				
		userService.initPassword(organizationCode, userId);														
								 					
		return toList(null, "비밀번호가 초기화되었습니다.");
	}	
			
}
