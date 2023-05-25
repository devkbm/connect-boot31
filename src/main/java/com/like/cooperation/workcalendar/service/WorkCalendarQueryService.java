package com.like.cooperation.workcalendar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.boundary.WorkCalendarDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarQueryRepository;


@Service
@Transactional(readOnly=true)
public class WorkCalendarQueryService {
				
	private WorkCalendarQueryRepository repository;
	
	public WorkCalendarQueryService(WorkCalendarQueryRepository repository) {		
		this.repository = repository;
	}
	
	public List<WorkCalendar> getWorkGroupList(WorkCalendarDTO.Search searchCondition) {
		return repository.getWorkGroupList(searchCondition);		
	}
			
	public List<WorkCalendar> getMyWorkGroupList(String userId) {
		return repository.getWorkGroupList(userId);
	}
}
