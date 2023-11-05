package com.like.cooperation.workcalendar.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventQueryDTO;
import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventResponseDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarEventQueryUseCase;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarEventQueryDbPort;

@Transactional(readOnly=true)
@Service
public class WorkCalendarEventQueryService implements WorkCalendarEventQueryUseCase {

	WorkCalendarEventQueryDbPort dbPort;
	
	public WorkCalendarEventQueryService(WorkCalendarEventQueryDbPort dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public List<WorkCalendarEventResponseDTO> getScheduleList(WorkCalendarEventQueryDTO searchCondition) {
		return this.dbPort.getScheduleList(searchCondition)
						  .stream()
						  .map(e -> WorkCalendarEventResponseDTO.toDTO(e))
						  .toList();
	}	
}
