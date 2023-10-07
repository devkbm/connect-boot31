package com.like.hrm.workchangeapp.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.workchangeapp.application.port.dto.WorkChangeCodeDTO;
import com.like.hrm.workchangeapp.application.service.DutyCodeCommandService;
import com.like.hrm.workchangeapp.domain.WorkChangeCode;
import com.like.system.core.message.MessageUtil;

@RestController
public class DutyCodeController {

	private DutyCodeCommandService service;		
	
	public DutyCodeController(DutyCodeCommandService service) {
		this.service = service;		
	}		
	
	@GetMapping("/api/hrm/dutycode/{id}")
	public ResponseEntity<?> getDutyCode(@PathVariable(value="id") String id) {
		
		WorkChangeCode entity = service.getDutyCode(id);
					
		return toOne(entity, MessageUtil.getQueryMessage(entity == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/dutycode")
	public ResponseEntity<?> saveDutyCode(@RequestBody WorkChangeCodeDTO.SaveDutyCode dto) {				
									
		service.saveDutyCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
		
	@DeleteMapping("/api/hrm/dutycode/{id}")
	public ResponseEntity<?> deleteDutyCode(@PathVariable(value="id") String id) {				
																		
		service.deleteDutyCode(id);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
