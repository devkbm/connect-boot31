package com.like.hrm.workchangeapp.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.workchangeapp.adapter.out.persistence.jparepository.WorkChangeCodeRepository;
import com.like.hrm.workchangeapp.application.port.dto.WorkChangeCodeDTO;
import com.like.hrm.workchangeapp.domain.WorkChangeCode;

@Service
@Transactional
public class DutyCodeCommandService {

	private WorkChangeCodeRepository repository;
			
	public DutyCodeCommandService(WorkChangeCodeRepository repository) {
		this.repository = repository;		
	}
	
	public WorkChangeCode getDutyCode(String dutyCode) {
		return this.repository.findById(dutyCode).orElse(null);
	}
	
	public void saveDutyCode(WorkChangeCode dutyCode) {
		this.repository.save(dutyCode);
	}
	
	public void saveDutyCode(WorkChangeCodeDTO.SaveDutyCode dto) {
		WorkChangeCode entity = repository.findById(dto.getDutyCode()).orElse(null);
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {			
			dto.modifyEntity(entity);
		}
				
		this.repository.save(entity);		
	}	
	
	public void deleteDutyCode(String dutyCode) {		
		this.repository.deleteById(dutyCode);
	}
		
	
}
