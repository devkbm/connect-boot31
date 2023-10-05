package com.like.hrm.workchangeapp.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.workchangeapp.adapter.out.persistence.jparepository.DutyApplicationRepository;
import com.like.hrm.workchangeapp.application.port.dto.DutyApplicationDTO;
import com.like.hrm.workchangeapp.domain.DutyApplication;
import com.like.system.holiday.domain.DateInfo;

@Service
@Transactional
public class DutyApplicationCommandService {

	private DutyApplicationRepository repository;	
	
	public DutyApplicationCommandService(DutyApplicationRepository repository) {
		this.repository = repository;		
	}
	
	public DutyApplication getDutyApplication(Long dutyId) {
		return repository.findById(dutyId).orElse(null);
	}
	
	public void saveDutyApplication(DutyApplicationDTO.Form dto) {
		DutyApplication entity = null;
		
		if (dto.dutyId() == null) {
			entity = dto.newEntity();
		} else {
			entity = this.getDutyApplication(dto.dutyId());
			dto.modifyEntity(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteDutyApplication(Long dutyId) {
		repository.deleteById(dutyId);		
	}
	
	public List<DateInfo> getDateList(LocalDate fromDate, LocalDate toDate) {
		return null; //this.holidayUtilService.getDateInfoList(fromDate, toDate).getDates();		
	}
	
}
