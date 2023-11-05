package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventResponseDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarEventQueryUseCase;
import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventQueryDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkCalendarEventQueryController {

	private WorkCalendarEventQueryUseCase service;
	
	public WorkCalendarEventQueryController(WorkCalendarEventQueryUseCase service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/workcalendarevent")
	public ResponseEntity<?> getScheduleList(@ModelAttribute WorkCalendarEventQueryDTO searchCondition) {										
		
		List<WorkCalendarEventResponseDTO> list = service.getScheduleList(searchCondition);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
}
