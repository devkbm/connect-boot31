package com.like.cooperation.workcalendar.application.port.in;

import java.util.List;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventQueryDTO;
import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventResponseDTO;

public interface WorkCalendarEventQueryUseCase {

	public List<WorkCalendarEventResponseDTO> getScheduleList(WorkCalendarEventQueryDTO searchCondition);
}
