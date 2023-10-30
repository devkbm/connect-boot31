package com.like.system.term.application.port.in;

import com.like.system.term.application.dto.DataDomainSaveDTO;

public interface DataDomainSelectUseCase {
	DataDomainSaveDTO select(String id);
	
}
