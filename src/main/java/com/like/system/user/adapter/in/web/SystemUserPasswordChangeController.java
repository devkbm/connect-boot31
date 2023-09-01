package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.user.application.port.in.SystemUserPasswordChangeUseCase;
import com.like.system.user.application.port.in.dto.PasswordChangeRequestDTO;

@RestController
public class SystemUserPasswordChangeController {		
				
	private SystemUserPasswordChangeUseCase useCase;
		
	public SystemUserPasswordChangeController(SystemUserPasswordChangeUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/system/user/{id}/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDTO dto) {				
						
		useCase.changePassword(dto);													
								 					
		return toList(null, "비밀번호가 변경되었습니다.");
	}			
			
}
