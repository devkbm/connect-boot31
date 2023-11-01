package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.in.BoardDeleteUseCase;
import com.like.cooperation.board.application.port.in.BoardSaveUseCase;
import com.like.cooperation.board.application.port.out.BoardCommandDbPort;
import com.like.cooperation.board.domain.Board;

@Service
public class BoardCommandService implements BoardSaveUseCase, BoardDeleteUseCase {

	BoardCommandDbPort dbPort;
	
	BoardCommandService(BoardCommandDbPort dbPort) {
		this.dbPort = dbPort;		
	}
	
	@Override
	public void save(BoardSaveDTO dto) {
		Board parentBoard = dto.boardParentId() == null ? null : this.dbPort.select(dto.boardParentId());
		
		this.dbPort.save(dto.toEntity(parentBoard));		
	}
	
	@Override
	public void delete(Long id) {
		this.dbPort.delete(id);		
	}
}
