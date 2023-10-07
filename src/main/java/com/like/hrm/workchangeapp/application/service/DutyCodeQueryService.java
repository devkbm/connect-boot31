package com.like.hrm.workchangeapp.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.workchangeapp.adapter.out.persistence.jparepository.WorkChangeCodeQueryRepository;
import com.like.hrm.workchangeapp.application.port.dto.WorkChangeCodeDTO;
import com.like.hrm.workchangeapp.domain.WorkChangeCode;

@Service
@Transactional(readOnly = true)
public class DutyCodeQueryService {

	private WorkChangeCodeQueryRepository repository;
	
	public DutyCodeQueryService(WorkChangeCodeQueryRepository dutyCodeRepository) {
		this.repository = dutyCodeRepository;
	}
	
	public List<WorkChangeCode> getDutyCodeList(WorkChangeCodeDTO.SearchDutyCode condition) {
		return this.repository.getDutyCodeList(condition);
	}
}
