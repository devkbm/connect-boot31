package com.like.cooperation.workcalendar.application.port.in;

import java.util.List;

import com.like.cooperation.workcalendar.application.dto.WorkCalendarEventDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

public interface WorkCalendarEventQueryUseCase {

	public List<WorkCalendarEvent> getScheduleList(WorkCalendarEventDTO.Search searchCondition);
}
