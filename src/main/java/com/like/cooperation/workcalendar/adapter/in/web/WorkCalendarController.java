package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarSaveDTO;
import com.like.cooperation.workcalendar.application.service.WorkCalendarService;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkCalendarController {	
		
	private WorkCalendarService service;				
		
	public WorkCalendarController(WorkCalendarService service) {
		this.service = service;
	}			
	
	@GetMapping("/api/grw/workcalendar/{id}")
	public ResponseEntity<?> getWorkGroup(@PathVariable Long id) {
						
		WorkCalendar entity = service.getWorkGroup(id);										
		
		WorkCalendarSaveDTO dto = WorkCalendarSaveDTO.toDTO(entity);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));													
	}
		
	@PostMapping("/api/grw/workcalendar")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkCalendarSaveDTO dto) {				
					
		service.saveWorkGroup(dto);		
		
		return toOne(dto, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/grw/workcalendar/{id}")
	public ResponseEntity<?> deleteWorkGroup(@PathVariable Long id) {
						
		service.deleteWorkGroup(id);
		
		return toOne(null, MessageUtil.getDeleteMessage(1));													
	}
			
}
