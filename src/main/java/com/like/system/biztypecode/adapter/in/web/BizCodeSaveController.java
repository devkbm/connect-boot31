package com.like.system.biztypecode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.application.port.in.dto.BizCodeDTO;
import com.like.system.biztypecode.application.service.BizCodeService;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeSaveController {

	private BizCodeService service;
	
	public BizCodeSaveController(BizCodeService service) {
		this.service = service;
	}
					
	@PostMapping("/api/system/bizcodetype/bizcode")	
	public ResponseEntity<?> saveBizCode(@RequestBody BizCodeDTO.Form dto) {				
																		
		service.saveBizCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}			
	
}
