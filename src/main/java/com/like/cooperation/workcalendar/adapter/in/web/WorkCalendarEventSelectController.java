package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventResponseDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarEventSelectUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkCalendarEventSelectController {

	WorkCalendarEventSelectUseCase useCase;
	
	WorkCalendarEventSelectController(WorkCalendarEventSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/grw/workcalendarevent/{id}")	
	public ResponseEntity<?> getSchedule(@PathVariable Long id) {											
		
		WorkCalendarEventResponseDTO dto = useCase.select(id);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));													
	}
}
