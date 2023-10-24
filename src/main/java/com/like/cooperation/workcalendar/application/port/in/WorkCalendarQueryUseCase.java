package com.like.cooperation.workcalendar.application.port.in;

import java.util.List;

import com.like.cooperation.workcalendar.application.dto.WorkCalendarDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;

public interface WorkCalendarQueryUseCase {

	public List<WorkCalendar> getWorkGroupList(WorkCalendarDTO.Search searchCondition);
	
	public List<WorkCalendar> getWorkGroupList(String userId);
}
