package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.in.BoardSelectUseCase;
import com.like.cooperation.board.application.port.out.BoardSelectDbPort;

@Service
public class BoardSelectService implements BoardSelectUseCase {

	BoardSelectDbPort port;
	
	BoardSelectService(BoardSelectDbPort port) {
		this.port = port;
	}
	
	@Override
	public BoardSaveDTO select(Long boardId) {		
		return this.port.select(boardId);
	}
	
}
