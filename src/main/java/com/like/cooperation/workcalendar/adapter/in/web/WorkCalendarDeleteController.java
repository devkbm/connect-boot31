package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.in.WorkCalendarDeleteUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkCalendarDeleteController {

	WorkCalendarDeleteUseCase useCase;
		
	WorkCalendarDeleteController(WorkCalendarDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/grw/workcalendar/{id}")
	public ResponseEntity<?> deleteWorkGroup(@PathVariable Long id) {
						
		useCase.delete(id);
		
		return toOne(null, MessageUtil.getDeleteMessage(1));													
	}
}
