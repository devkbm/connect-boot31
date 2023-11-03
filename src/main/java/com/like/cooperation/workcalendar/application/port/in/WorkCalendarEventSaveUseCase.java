package com.like.cooperation.workcalendar.application.port.in;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventSaveDTO;

public interface WorkCalendarEventSaveUseCase {

	void save(WorkCalendarEventSaveDTO dto);
}
