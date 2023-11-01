package com.like.cooperation.workcalendar.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventDTO;
import com.like.cooperation.workcalendar.application.service.WorkCalendarEventQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class WorkCalendarEventQueryController {

	private WorkCalendarEventQueryService service;
	
	public WorkCalendarEventQueryController(WorkCalendarEventQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/workcalendarevent")
	public ResponseEntity<?> getScheduleList(@ModelAttribute WorkCalendarEventDTO.Search searchCondition) {										
		
		List<WorkCalendarEventDTO.Response> list = service.getScheduleList(searchCondition)
												 .stream()
												 .map( r -> WorkCalendarEventDTO.Response.convert(r))
												 .toList();
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
}
