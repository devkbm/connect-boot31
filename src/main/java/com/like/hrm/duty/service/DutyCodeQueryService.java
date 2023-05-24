package com.like.hrm.duty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.dutycode.boundary.DutyCodeDTO;
import com.like.hrm.dutycode.domain.DutyCode;
import com.like.hrm.dutycode.domain.DutyCodeQueryRepository;

@Service
@Transactional(readOnly = true)
public class DutyCodeQueryService {

	private DutyCodeQueryRepository repository;
	
	public DutyCodeQueryService(DutyCodeQueryRepository dutyCodeRepository) {
		this.repository = dutyCodeRepository;
	}
	
	public List<DutyCode> getDutyCodeList(DutyCodeDTO.SearchDutyCode condition) {
		return this.repository.getDutyCodeList(condition);
	}
}
