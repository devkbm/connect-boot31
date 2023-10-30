package com.like.system.term.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.application.dto.DataDomainSaveDTO;
import com.like.system.term.application.port.in.DataDomainSelectUseCase;

@RestController
public class DataDomainSelectController {

	DataDomainSelectUseCase useCase;
	
	DataDomainSelectController(DataDomainSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/datadomin/{id}")
	public ResponseEntity<?> get(@PathVariable String id) {
		
		DataDomainSaveDTO dto = useCase.select(id);								
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
}
