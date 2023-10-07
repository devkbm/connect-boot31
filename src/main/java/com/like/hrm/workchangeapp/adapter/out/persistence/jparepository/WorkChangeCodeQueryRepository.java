package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import java.util.List;

import com.like.hrm.workchangeapp.application.port.dto.WorkChangeCodeDTO;
import com.like.hrm.workchangeapp.domain.WorkChangeCode;

public interface WorkChangeCodeQueryRepository {
	List<WorkChangeCode> getDutyCodeList(WorkChangeCodeDTO.SearchDutyCode condition);
}
