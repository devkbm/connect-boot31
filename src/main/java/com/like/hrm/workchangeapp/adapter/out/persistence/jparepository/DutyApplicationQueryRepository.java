package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import java.time.LocalDate;
import java.util.List;

import com.like.hrm.dutycode.domain.DutyCode;
import com.like.hrm.workchangeapp.application.port.dto.DutyApplicationDTO;
import com.like.hrm.workchangeapp.domain.DutyApplication;

public interface DutyApplicationQueryRepository {

	long getDutyApplicationCount(String staffId, List<DutyCode> dutyCodeList, LocalDate fromDate, LocalDate toDate);
	
	List<DutyApplication> getDutyApplicationList(DutyApplicationDTO.Search condition);
}
