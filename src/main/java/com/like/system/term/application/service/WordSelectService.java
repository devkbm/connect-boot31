package com.like.system.term.application.service;

import org.springframework.stereotype.Service;

import com.like.system.term.application.dto.WordSaveDTO;
import com.like.system.term.application.port.in.WordSelectUseCase;
import com.like.system.term.application.port.out.WordCommandDbPort;

@Service
public class WordSelectService implements WordSelectUseCase {

	WordCommandDbPort dbPort;
	
	WordSelectService(WordCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public WordSaveDTO select(String id) {
		return WordSaveDTO.toDTO(this.dbPort.select(id));
	}

}
