package com.like.system.user.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.application.port.in.dto.SystemUserQueryConditionDTO;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;

@RestController
public class SystemUserQueryController {

	private SystemUserSelectUseCase useCase;
	
	public SystemUserQueryController(SystemUserSelectUseCase useCase) {
		this.useCase = useCase;
	}
		
	@GetMapping("/api/system/user")
	public ResponseEntity<?> getUserList(SystemUserQueryConditionDTO dto) throws FileNotFoundException, IOException {
												
		List<SystemUserSaveDTO> dtoList = useCase.selectList(dto);			
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
