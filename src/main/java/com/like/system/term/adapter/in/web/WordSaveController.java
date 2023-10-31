package com.like.system.term.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.application.dto.WordSaveDTO;
import com.like.system.term.application.port.in.WordSaveUseCase;

import jakarta.validation.Valid;

@RestController
public class WordSaveController {

	WordSaveUseCase useCase;
	
	WordSaveController(WordSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/system/word")
	public ResponseEntity<?> saveWord(@Valid @RequestBody WordSaveDTO dto) {
														
		useCase.save(dto);										
		
		return toList(null, MessageUtil.getSaveMessage(1));
	
	}
}
