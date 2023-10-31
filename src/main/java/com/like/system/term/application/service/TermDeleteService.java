package com.like.system.term.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.port.in.TermDeleteUseCase;
import com.like.system.term.application.port.out.TermCommandDbPort;

@Transactional
@Service
public class TermDeleteService implements TermDeleteUseCase {
	
	TermCommandDbPort dbPort;
	
	TermDeleteService(TermCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String id) {
		this.dbPort.delete(id);
	}

}
