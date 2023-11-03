package com.like.cooperation.workcalendar.application.port.out;

import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

public interface WorkCalendarEventCommandDbPort {
	WorkCalendarEvent select(Long id);
	
	void save(WorkCalendarEvent entity);
	
	void delete(Long id); 
}
