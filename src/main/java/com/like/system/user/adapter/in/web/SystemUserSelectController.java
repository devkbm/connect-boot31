package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;


@RestController
public class SystemUserSelectController {		
				
	private SystemUserSelectUseCase useCase;
		
	public SystemUserSelectController(SystemUserSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	
	@GetMapping("/api/system/user/{userId}")
	public ResponseEntity<?> getUser(@RequestParam String organizationCode, @PathVariable String userId) throws FileNotFoundException, IOException {
												
		SystemUserSaveDTO dto = useCase.selectDTO(organizationCode, userId);					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}			
			
}
