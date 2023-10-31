package com.like.system.term.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.dto.WordSaveDTO;
import com.like.system.term.application.port.in.WordSaveUseCase;
import com.like.system.term.application.port.out.WordCommandDbPort;

@Transactional
@Service
public class WordSaveService implements WordSaveUseCase {

	WordCommandDbPort dbPort;
	
	WordSaveService(WordCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	@Override
	public void save(WordSaveDTO dto) {
		this.dbPort.save(dto.newEntity());
	}

}
