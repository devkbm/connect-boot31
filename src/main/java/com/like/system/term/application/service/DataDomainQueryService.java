package com.like.system.term.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.term.application.dto.DataDomainSaveDTO;
import com.like.system.term.application.port.in.DataDomainQueryUseCase;
import com.like.system.term.application.port.out.DataDomainQueryDbPort;

@Service
public class DataDomainQueryService implements DataDomainQueryUseCase {

	DataDomainQueryDbPort dbPort;
	
	DataDomainQueryService(DataDomainQueryDbPort dbPort) {
		this.dbPort = dbPort;				
	}
	
	@Override
	public List<DataDomainSaveDTO> selectList() {
		return this.dbPort.select()
						  .stream()
						  .map(e -> DataDomainSaveDTO.toDTO(e))
						  .toList();
	}

}
