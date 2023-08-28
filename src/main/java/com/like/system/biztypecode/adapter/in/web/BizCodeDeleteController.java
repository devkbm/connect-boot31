package com.like.system.biztypecode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.application.service.BizCodeService;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeDeleteController {

	private BizCodeService service;
	
	public BizCodeDeleteController(BizCodeService service) {
		this.service = service;
	}		
		
	@DeleteMapping("/api/system/bizcodetype/{typeId}/bizcode/{code}")
	public ResponseEntity<?> deleteBizCode(@RequestParam String organizationCode
										  ,@PathVariable String typeId
			 							  ,@PathVariable String code) {				
																		
		service.deleteBizCode(organizationCode, typeId, code);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
}
