package com.like.cooperation.workcalendar.domain;

import java.util.List;

import com.like.cooperation.workcalendar.boundary.WorkCalendarEventDTO;

public interface WorkCalendarEventQueryRepository {

	public List<WorkCalendarEvent> getScheduleList(WorkCalendarEventDTO.Search searchCondition);
}
