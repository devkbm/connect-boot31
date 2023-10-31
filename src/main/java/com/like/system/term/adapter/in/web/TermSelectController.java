package com.like.system.term.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.application.dto.TermSaveDTO;
import com.like.system.term.application.port.in.TermSelectUseCase;

@RestController
public class TermSelectController {

	TermSelectUseCase useCase;
	
	TermSelectController(TermSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/terms/{id}")
	public ResponseEntity<?> getTerm(@PathVariable String id) {
		
		TermSaveDTO term = useCase.select(id);								
		
		return toOne(term, MessageUtil.getQueryMessage(term == null ? 0 : 1));
	}		
}
