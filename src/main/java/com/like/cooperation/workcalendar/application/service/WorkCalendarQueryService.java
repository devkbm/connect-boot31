package com.like.cooperation.workcalendar.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.application.dto.WorkCalendarDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarQueryUseCase;
import com.like.cooperation.workcalendar.domain.WorkCalendar;


@Service
@Transactional(readOnly=true)
public class WorkCalendarQueryService {
				
	private WorkCalendarQueryUseCase repository;
	
	public WorkCalendarQueryService(WorkCalendarQueryUseCase repository) {		
		this.repository = repository;
	}
	
	public List<WorkCalendar> getWorkGroupList(WorkCalendarDTO.Search searchCondition) {
		return repository.getWorkGroupList(searchCondition);		
	}
			
	public List<WorkCalendar> getMyWorkGroupList(String userId) {
		return repository.getWorkGroupList(userId);
	}
}
