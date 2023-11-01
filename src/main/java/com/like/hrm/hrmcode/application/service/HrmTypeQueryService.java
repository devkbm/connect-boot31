package com.like.hrm.hrmcode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.hrmcode.adapter.out.persistence.HrmCodeQueryRepository;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeDTO;
import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeType;

@Service
@Transactional(readOnly = true)
public class HrmTypeQueryService {

	private HrmCodeQueryRepository repository;	
		
	public HrmTypeQueryService(HrmCodeQueryRepository repository) {		
		this.repository = repository;
	}
				
	public List<HrmCodeType> getHrmTypeList(HrmCodeTypeDTO.Search condition) {
		return repository.getHrmCodeTypeList(condition);
	}
	
	public List<HrmCode> getTypeDetailCodeList(HrmCodeDTO.Search condition) {
		return repository.getHrmCodeList(condition);
	}	
	
	
	
}
