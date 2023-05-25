package com.like.cooperation.workcalendar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.boundary.WorkCalendarEventDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;
import com.like.cooperation.workcalendar.domain.WorkCalendarEventQueryRepository;

@Service
@Transactional(readOnly=true)
public class WorkCalendarEventQueryService {

	private WorkCalendarEventQueryRepository repository;
	
	public WorkCalendarEventQueryService(WorkCalendarEventQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<WorkCalendarEvent> getScheduleList(WorkCalendarEventDTO.Search searchCondition) {
		return repository.getScheduleList(searchCondition);		
	}
}
