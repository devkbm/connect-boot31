package com.like.system.term.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.dto.WordSaveDTO;
import com.like.system.term.application.port.in.WordQueryUseCase;
import com.like.system.term.application.port.out.WordQueryDbPort;

@Transactional(readOnly = true)
@Service
public class WordQueryService implements WordQueryUseCase {

	WordQueryDbPort dbPort;
	
	WordQueryService(WordQueryDbPort dbPort) {
		this.dbPort = dbPort;
	}	
	
	@Override
	public List<WordSaveDTO> select() {
		return this.dbPort.select()
						  .stream()
						  .map(e -> WordSaveDTO.toDTO(e))
						  .toList();
	}

}
