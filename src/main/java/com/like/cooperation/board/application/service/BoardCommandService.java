package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.in.BoardDeleteUseCase;
import com.like.cooperation.board.application.port.in.BoardSaveUseCase;
import com.like.cooperation.board.application.port.out.BoardCommandDbPort;

@Service
public class BoardCommandService implements BoardSaveUseCase, BoardDeleteUseCase {

	BoardCommandDbPort port;
	
	BoardCommandService(BoardCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public void save(BoardSaveDTO dto) {
		this.port.save(dto);		
	}
	
	@Override
	public void delete(Long boardId) {
		this.port.delete(boardId);		
	}
}
