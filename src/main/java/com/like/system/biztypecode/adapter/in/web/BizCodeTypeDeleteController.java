package com.like.system.biztypecode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.application.port.in.BizCodeTypeDeleteUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeTypeDeleteController {

	private BizCodeTypeDeleteUseCase useCase;
	
	public BizCodeTypeDeleteController(BizCodeTypeDeleteUseCase useCase) {
		this.useCase = useCase;
	}	
		
	@DeleteMapping("/api/system/bizcodetype/{typeId}")
	public ResponseEntity<?> deleteBizCodeType(@RequestParam String organizationCode
											  ,@PathVariable String typeId) {				
																		
		useCase.delete(organizationCode, typeId);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
