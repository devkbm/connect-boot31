package com.like.system.term.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.application.dto.TermSaveDTO;
import com.like.system.term.application.service.TermService;

@RestController
public class TermController {
	
	private TermService service;

	public TermController(TermService service) {
		this.service = service;
	}
		
	@GetMapping("/api/system/terms/{id}")
	public ResponseEntity<?> getTerm(@PathVariable String id) {
		
		TermSaveDTO term = TermSaveDTO.convert(service.get(id));								
		
		return toOne(term, MessageUtil.getQueryMessage(term == null ? 0 : 1));
	}			
		
	@PostMapping("/api/system/terms")
	public ResponseEntity<?> saveTerm(@Valid @RequestBody TermSaveDTO dto) {
														
		service.save(dto);										
		
		return toList(null, MessageUtil.getSaveMessage(1));
	
	}
					
	@DeleteMapping("/api/system/terms/{id}")
	public ResponseEntity<?> delTerm(@PathVariable String id) {
								
		service.delete(id);										
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}		
	
}