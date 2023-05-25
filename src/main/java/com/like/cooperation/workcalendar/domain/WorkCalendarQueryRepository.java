package com.like.cooperation.workcalendar.domain;

import java.util.List;

import com.like.cooperation.workcalendar.boundary.WorkCalendarDTO;

public interface WorkCalendarQueryRepository {

	public List<WorkCalendar> getWorkGroupList(WorkCalendarDTO.Search searchCondition);
	
	public List<WorkCalendar> getWorkGroupList(String userId);
}
