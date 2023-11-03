package com.like.cooperation.workcalendar.application.port.in;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventResponseDTO;

public interface WorkCalendarEventSelectUseCase {
	WorkCalendarEventResponseDTO select(Long id);
}
