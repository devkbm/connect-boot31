package com.like.system.authority.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.authority.application.port.in.AuthoritySelectUseCase;
import com.like.system.authority.domain.Authority;

@RestController
public class AuthorityDuplicationCheckController {

	AuthoritySelectUseCase useCase;
	
	public AuthorityDuplicationCheckController(AuthoritySelectUseCase useCase) {
		this.useCase = useCase;	
	}
	
	@GetMapping("/api/system/authority/{authorityName}/check")
	public ResponseEntity<?> getAuthorityDupCheck(@RequestParam String organizationCode,@PathVariable String authorityName) {			
					
		Authority authority = useCase.select(organizationCode, authorityName);										
		
		boolean rtn = authority == null ? true : false;
						
		return toOne(rtn, rtn == false? "기존에 등록된 권한이 존재합니다." : "신규 등록 가능합니다.");
	}
}
