package com.like.cooperation.workcalendar.web;

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

import com.like.cooperation.workcalendar.boundary.WorkCalendarEventDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;
import com.like.cooperation.workcalendar.service.WorkCalendarEventService;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkCalendarEventController {

	private WorkCalendarEventService service;
	
	public WorkCalendarEventController(WorkCalendarEventService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/workcalendarevent/{id}")	
	public ResponseEntity<?> getSchedule(@PathVariable Long id) {
						
		WorkCalendarEvent entity = service.getSchedule(id);							
		
		WorkCalendarEventDTO.Response dto = WorkCalendarEventDTO.Response.convert(entity);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));													
	}
		
	@PostMapping("/api/grw/workcalendarevent")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkCalendarEventDTO.Form dto) {				
		
		service.saveSchedule(dto);		
										 					
		return toOne(dto, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/grw/workcalendarevent/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable(value="id") Long id) {
						
		service.deleteSchedule(id);							
				
		return toList(null, MessageUtil.getDeleteMessage(1));													
	}
}
