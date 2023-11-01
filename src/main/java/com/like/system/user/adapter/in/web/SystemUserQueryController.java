package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserQueryUseCase;

@RestController
public class SystemUserQueryController {

	private SystemUserQueryUseCase useCase;
	
	public SystemUserQueryController(SystemUserQueryUseCase useCase) {
		this.useCase = useCase;
	}
		
	@GetMapping("/api/system/user")
	public ResponseEntity<?> getUserList(SystemUserQueryDTO dto) throws FileNotFoundException, IOException {
												
		List<SystemUserSaveDTO> dtoList = useCase.selectList(dto);			
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
