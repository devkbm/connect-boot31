package com.like.cooperation.workcalendar.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.workcalendar.boundary.WorkCalendarDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.service.WorkCalendarQueryService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;

@RestController
public class WorkCalendarQueryController {

	private WorkCalendarQueryService service;
	
	public WorkCalendarQueryController(WorkCalendarQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/grw/workcalendar")
	public ResponseEntity<?> getWorkGroupList(@ModelAttribute WorkCalendarDTO.Search searchCondition) {
						
		List<WorkCalendar> list = service.getWorkGroupList(searchCondition);				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
	
	@GetMapping("/api/grw/myworkcalendar")
	public ResponseEntity<?> getWorkGroupList() {							
		
		List<WorkCalendar> list = service.getMyWorkGroupList(SessionUtil.getUserId());				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
}
