package com.like.system.term.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.dto.TermSaveDTO;
import com.like.system.term.application.port.in.TermSelectUseCase;
import com.like.system.term.application.port.out.TermCommandDbPort;

@Transactional
@Service
public class TermSelectService implements TermSelectUseCase {

	TermCommandDbPort dbPort;
	
	TermSelectService(TermCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public TermSaveDTO select(String id) {
		return TermSaveDTO.toDTO(this.dbPort.select(id));
	}

}
