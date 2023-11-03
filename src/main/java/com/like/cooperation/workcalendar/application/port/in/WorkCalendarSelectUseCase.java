package com.like.cooperation.workcalendar.application.port.in;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarSaveDTO;

public interface WorkCalendarSelectUseCase {

	WorkCalendarSaveDTO select(Long id);
}
