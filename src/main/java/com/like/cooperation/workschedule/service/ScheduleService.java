package com.like.cooperation.workschedule.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.cooperation.workschedule.boundary.ScheduleDTO;
import com.like.cooperation.workschedule.domain.Schedule;
import com.like.cooperation.workschedule.domain.ScheduleRepository;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.domain.WorkGroupRepository;

@Service
@Transactional
public class ScheduleService {

	private ScheduleRepository repository;
	private WorkGroupRepository workGroupRepository;
	
	public ScheduleService(ScheduleRepository repository
						  ,WorkGroupRepository workGroupRepository) {
		this.repository = repository;
		this.workGroupRepository = workGroupRepository;
	}
	
	public Schedule getSchedule(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveSchedule(Schedule schedule) {
		repository.save(schedule);
	}
	
	public void saveSchedule(ScheduleDTO.Form dto) {
		WorkGroup workGroup = workGroupRepository.findById(dto.workGroupId()).orElse(null);
		Schedule entity = null; 
		
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
