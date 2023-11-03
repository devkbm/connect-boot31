package com.like.cooperation.workcalendar.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.workcalendar.adapter.out.persistence.jparepository.WorkCalendarEventJpaRepository;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarEventCommandDbPort;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

@Repository
public class WorkCalendarEventCommandDbAdapter implements WorkCalendarEventCommandDbPort {

	WorkCalendarEventJpaRepository repository;
	
	WorkCalendarEventCommandDbAdapter(WorkCalendarEventJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public WorkCalendarEvent select(Long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public void save(WorkCalendarEvent entity) {
		this.repository.save(entity);		
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

}
