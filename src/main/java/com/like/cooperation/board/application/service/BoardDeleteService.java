package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.in.BoardDeleteUseCase;
import com.like.cooperation.board.application.port.out.BoardDeleteDbPort;

@Service
public class BoardDeleteService implements BoardDeleteUseCase {

	BoardDeleteDbPort port;
	
	BoardDeleteService(BoardDeleteDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(Long boardId) {
		this.port.delete(boardId);		
	}

}
