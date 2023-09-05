package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.in.BoardSaveUseCase;
import com.like.cooperation.board.application.port.out.BoardSaveDbPort;

@Service
public class BoardSaveService implements BoardSaveUseCase {

	BoardSaveDbPort port;
	
	BoardSaveService(BoardSaveDbPort port) {
		this.port = port;
	}
	
	@Override
	public void save(BoardSaveDTO dto) {
		this.port.save(dto);		
	}

}
