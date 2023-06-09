package com.like.cooperation.workcalendar.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.boundary.WorkCalendarDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.service.WorkCalendarService;
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
		
		WorkCalendarDTO.Form dto = WorkCalendarDTO.Form.convertDTO(entity);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));													
	}
		
	@PostMapping("/api/grw/workcalendar")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkCalendarDTO.Form dto) {				
					
		service.saveWorkGroup(dto);		
		
		return toOne(dto, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/grw/workcalendar/{id}")
	public ResponseEntity<?> deleteWorkGroup(@PathVariable Long id) {
						
		service.deleteWorkGroup(id);
		
		return toOne(null, MessageUtil.getDeleteMessage(1));													
	}
			
}
