package com.like.hrm.workchangeapp.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.workchangeapp.adapter.out.persistence.jparepository.DutyApplicationQueryRepository;
import com.like.hrm.workchangeapp.application.port.dto.DutyApplicationDTO;
import com.like.hrm.workchangeapp.domain.WorkChangeApplication;

@Service
@Transactional(readOnly = true)
public class DutyApplicationQueryService {

	private DutyApplicationQueryRepository repository;
	
	public DutyApplicationQueryService(DutyApplicationQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<WorkChangeApplication> getDutyApplicationList(DutyApplicationDTO.Search condition) {
		return this.repository.getDutyApplicationList(condition);
	}
}
