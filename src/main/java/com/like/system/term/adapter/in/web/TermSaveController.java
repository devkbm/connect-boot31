package com.like.system.term.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.application.dto.TermSaveDTO;
import com.like.system.term.application.port.in.TermSaveUseCase;

import jakarta.validation.Valid;

@RestController
public class TermSaveController {

	TermSaveUseCase useCase;
	
	TermSaveController(TermSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/system/terms")
	public ResponseEntity<?> saveTerm(@Valid @RequestBody TermSaveDTO dto) {
														
		useCase.save(dto);										
		
		return toList(null, MessageUtil.getSaveMessage(1));	
	}
	
}
