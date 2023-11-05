package com.like.cooperation.workcalendar.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventSaveDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarEventSaveUseCase;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarCommandDbPort;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarEventCommandDbPort;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

@Transactional
@Service
public class WorkCalendarEventSaveService implements WorkCalendarEventSaveUseCase {
	
	WorkCalendarEventCommandDbPort dbPort;
	WorkCalendarCommandDbPort calendarDbPort;
	
	WorkCalendarEventSaveService(WorkCalendarEventCommandDbPort dbPort,
								 WorkCalendarCommandDbPort calendarDbPort) {
		this.dbPort = dbPort;
		this.calendarDbPort = calendarDbPort;
	}
	
	@Override
	public void save(WorkCalendarEventSaveDTO dto) {
		WorkCalendar workGroup = calendarDbPort.select(dto.workCalendarId());
		WorkCalendarEvent entity = null; 
		
		if (dto.id() != null) {
			entity = dbPort.select(dto.id());
		}
		
		if (entity == null) {
			entity = dto.newSchedule(workGroup);
		} else {
			dto.modifySchedule(entity);
		}
		
		dbPort.save(entity);
	}

}
