package com.like.cooperation.board.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.BoardJpaRepository;
import com.like.cooperation.board.application.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.out.BoardCommandDbPort;
import com.like.cooperation.board.domain.Board;

@Repository
public class BoardDbAdapter implements BoardCommandDbPort {

	BoardJpaRepository repository;
	
	BoardDbAdapter(BoardJpaRepository repository) {
		this.repository = repository;
	}


	@Override
	public void save(BoardSaveDTO dto) {
		Board parentBoard = dto.boardParentId() == null ? null : this.repository.findById(dto.boardParentId()).orElse(null);
		
		this.repository.save(dto.toEntity(parentBoard));		
	}

	@Override
	public void delete(Long boardId) {
		this.repository.deleteById(boardId);		
	}
}
