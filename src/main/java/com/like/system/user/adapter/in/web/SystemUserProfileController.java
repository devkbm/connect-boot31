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
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;

@RestController
public class SystemUserProfileController {		
				
	private SystemUserSelectUseCase useCase;
		
	public SystemUserProfileController(SystemUserSelectUseCase useCase) {
		this.useCase = useCase;
	}

	@GetMapping("/api/system/user/my-profile")
	public ResponseEntity<?> getUserProfile(@RequestParam String organizationCode) throws FileNotFoundException, IOException {																		
		
		SystemUserSaveDTO dto = useCase.selectDTO(organizationCode, SessionUtil.getUserId());					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}			
}
