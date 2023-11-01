package com.like.cooperation.workcalendar.application.port.in;

import java.util.List;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarQueryDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;

public interface WorkCalendarQueryUseCase {

	public List<WorkCalendar> getWorkGroupList(WorkCalendarQueryDTO searchCondition);
	
	public List<WorkCalendar> getWorkGroupList(String userId);
}
