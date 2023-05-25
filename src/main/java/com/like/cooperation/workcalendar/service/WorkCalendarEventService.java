package com.like.cooperation.workcalendar.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.cooperation.workcalendar.boundary.WorkCalendarEventDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;
import com.like.cooperation.workcalendar.domain.WorkCalendarEventRepository;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarRepository;

@Service
@Transactional
public class WorkCalendarEventService {

	private WorkCalendarEventRepository repository;
	private WorkCalendarRepository workGroupRepository;
	
	public WorkCalendarEventService(WorkCalendarEventRepository repository
						  ,WorkCalendarRepository workGroupRepository) {
		this.repository = repository;
		this.workGroupRepository = workGroupRepository;
	}
	
	public WorkCalendarEvent getSchedule(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveSchedule(WorkCalendarEvent schedule) {
		repository.save(schedule);
	}
	
	public void saveSchedule(WorkCalendarEventDTO.Form dto) {
		WorkCalendar workGroup = workGroupRepository.findById(dto.workCalendarId()).orElse(null);
		WorkCalendarEvent entity = null; 
		
		if (dto.id() != null) {
			entity = repository.findById(dto.id()).orElse(null);
		}
		
		if (entity == null) {
			entity = dto.newSchedule(workGroup);
		} else {
			dto.modifySchedule(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteSchedule(Long id) {		
		repository.deleteById(id);
	}
}
