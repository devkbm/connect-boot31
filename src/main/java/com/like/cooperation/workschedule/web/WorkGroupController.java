package com.like.cooperation.workschedule.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.service.WorkGroupService;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkGroupController {	
		
	private WorkGroupService workGroupService;				
		
	public WorkGroupController(WorkGroupService workGroupService) {
		this.workGroupService = workGroupService;
	}			
	
	@GetMapping("/api/grw/workgroup/{id}")
	public ResponseEntity<?> getWorkGroup(@PathVariable Long id) {
						
		WorkGroup entity = workGroupService.getWorkGroup(id);										
		
		WorkDTO.Form dto = WorkDTO.Form.convertDTO(entity);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));													
	}
		
	@PostMapping("/api/grw/workgroup")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkDTO.Form dto) {				
					
		workGroupService.saveWorkGroup(dto);		
		
		return toOne(dto, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/grw/workgroup/{id}")
	public ResponseEntity<?> deleteWorkGroup(@PathVariable Long id) {
						
		workGroupService.deleteWorkGroup(id);
		
		return toOne(null, MessageUtil.getDeleteMessage(1));													
	}
			
}
