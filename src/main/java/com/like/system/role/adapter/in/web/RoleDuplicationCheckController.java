package com.like.system.role.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.role.application.port.in.RoleSelectUseCase;
import com.like.system.role.domain.Role;

@RestController
public class RoleDuplicationCheckController {

	RoleSelectUseCase useCase;
	
	public RoleDuplicationCheckController(RoleSelectUseCase useCase) {
		this.useCase = useCase;	
	}
	
	@GetMapping("/api/system/role/{roleName}/check")
	public ResponseEntity<?> getAuthorityDupCheck(@RequestParam String organizationCode,@PathVariable String roleName) {			
					
		Role authority = useCase.select(organizationCode, roleName);										
		
		boolean rtn = authority == null ? true : false;
						
		return toOne(rtn, rtn == false? "기존에 등록된 권한이 존재합니다." : "신규 등록 가능합니다.");
	}
}
