package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventSaveDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarEventSaveUseCase;
import com.like.system.core.message.MessageUtil;

import jakarta.validation.Valid;

@RestController
public class WorkCalendarEventSaveController {

	WorkCalendarEventSaveUseCase useCase;
	
	WorkCalendarEventSaveController(WorkCalendarEventSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/grw/workcalendarevent")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkCalendarEventSaveDTO dto) {				
		
		useCase.save(dto);		
										 					
		return toOne(dto, MessageUtil.getSaveMessage(1));
	}
}
