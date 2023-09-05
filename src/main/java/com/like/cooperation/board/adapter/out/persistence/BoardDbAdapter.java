package com.like.cooperation.board.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.BoardJpaRepository;
import com.like.cooperation.board.application.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.out.BoardDeleteDbPort;
import com.like.cooperation.board.application.port.out.BoardSaveDbPort;
import com.like.cooperation.board.application.port.out.BoardSelectDbPort;
import com.like.cooperation.board.domain.Board;

@Repository
public class BoardDbAdapter implements BoardSelectDbPort, BoardSaveDbPort, BoardDeleteDbPort {

	BoardJpaRepository repository;
	
	BoardDbAdapter(BoardJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public BoardSaveDTO select(Long boardId) {
		Board entity = this.repository.findById(boardId).orElse(null);
		return BoardSaveDTO.toDTO(entity);
	}

	@Override
	public void save(BoardSaveDTO dto) {
		Board parentBoard = this.repository.findById(dto.boardParentId()).orElse(null);
		
		this.repository.save(dto.newBoard(parentBoard));		
	}

	@Override
	public void delete(Long boardId) {
		this.repository.deleteById(boardId);		
	}
}
