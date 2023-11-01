package com.like.cooperation.workcalendar.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarEventQueryUseCase;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

@Service
@Transactional(readOnly=true)
public class WorkCalendarEventQueryService {

	private WorkCalendarEventQueryUseCase repository;
	
	public WorkCalendarEventQueryService(WorkCalendarEventQueryUseCase repository) {
		this.repository = repository;
	}
	
	public List<WorkCalendarEvent> getScheduleList(WorkCalendarEventDTO.Search searchCondition) {
		return repository.getScheduleList(searchCondition);		
	}
}
