package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarSaveDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarSelectUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkCalendarSelectController {

	WorkCalendarSelectUseCase useCase;
	
	WorkCalendarSelectController(WorkCalendarSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/grw/workcalendar/{id}")
	public ResponseEntity<?> getWorkGroup(@PathVariable Long id) {																
		
		WorkCalendarSaveDTO dto = useCase.select(id);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));													
	}
}
