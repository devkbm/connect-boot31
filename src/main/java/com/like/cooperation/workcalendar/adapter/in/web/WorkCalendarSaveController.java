package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarSaveDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarSaveUseCase;
import com.like.system.core.message.MessageUtil;

import jakarta.validation.Valid;

@RestController
public class WorkCalendarSaveController {

	WorkCalendarSaveUseCase useCase;
	
	WorkCalendarSaveController(WorkCalendarSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/grw/workcalendar")
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkCalendarSaveDTO dto) {				
					
		useCase.save(dto);		
		
		return toOne(dto, MessageUtil.getSaveMessage(1));
	}
}
