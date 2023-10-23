package com.like.system.dept.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.dept.application.port.dto.DeptSaveDTO;
import com.like.system.dept.application.port.in.DeptSaveUseCase;

@RestController
public class DeptSaveController {
	
	private DeptSaveUseCase useCase;	
	
	public DeptSaveController(DeptSaveUseCase useCase) {
		this.useCase = useCase;		
	}
		
	@PostMapping("/api/system/dept")
	public ResponseEntity<?> saveDept(@Valid @RequestBody DeptSaveDTO dto) {			
																
		useCase.save(dto);		
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}			
	
}
