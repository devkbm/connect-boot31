package com.like.system.term.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.port.in.WordDeleteUseCase;
import com.like.system.term.application.port.out.WordCommandDbPort;

@Transactional
@Service
public class WordDeleteService implements WordDeleteUseCase {

	WordCommandDbPort dbPort;
	
	WordDeleteService(WordCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String id) {
		this.dbPort.delete(id);
	}

}
