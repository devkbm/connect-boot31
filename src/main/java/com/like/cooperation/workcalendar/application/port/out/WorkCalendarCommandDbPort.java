package com.like.cooperation.workcalendar.application.port.out;

import com.like.cooperation.workcalendar.domain.WorkCalendar;

public interface WorkCalendarCommandDbPort {
	WorkCalendar select(Long id);
	
	void save(WorkCalendar entity);
	
	void delete(Long id);
}
