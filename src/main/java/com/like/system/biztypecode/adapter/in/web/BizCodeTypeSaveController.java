package com.like.system.biztypecode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.application.port.in.BizCodeTypeSaveUseCase;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeTypeSaveController {

	private BizCodeTypeSaveUseCase useCase;
	
	public BizCodeTypeSaveController(BizCodeTypeSaveUseCase useCase) {
		this.useCase = useCase;
	}
			
	@PostMapping("/api/system/bizcodetype")	
	public ResponseEntity<?> saveBizCodeType(@RequestBody BizCodeTypeSaveDTO dto) {				
																			
		useCase.save(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
}
