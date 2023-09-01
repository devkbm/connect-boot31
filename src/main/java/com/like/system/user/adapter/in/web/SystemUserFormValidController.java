package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.user.application.port.in.SystemUserSelectUseCase;

@RestController
public class SystemUserFormValidController {
	
	private SystemUserSelectUseCase useCase;
		
	public SystemUserFormValidController(SystemUserSelectUseCase useCase) {
		this.useCase = useCase;
	}

	@GetMapping("/api/system/user/{userId}/check")
	public ResponseEntity<?> checkId(@RequestParam String organizationCode, @PathVariable String userId) {
						
		boolean isDuplicated = useCase.selectDTO(organizationCode, userId) == null ? false : true;					
				
		return toOne(isDuplicated ? false : true, isDuplicated ? "기존 아이디가 존재합니다." : "신규 등록 가능합니다."); 
	}	
}
