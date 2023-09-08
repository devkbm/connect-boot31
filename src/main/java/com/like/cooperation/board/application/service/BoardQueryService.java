package com.like.cooperation.board.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.application.dto.BoardHierarchy;
import com.like.cooperation.board.application.dto.BoardQueryConditionDTO;
import com.like.cooperation.board.application.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.in.BoardQueryUseCase;
import com.like.cooperation.board.application.port.out.BoardQueryDbPort;

@Service
@Transactional(readOnly=true)
public class BoardQueryService implements BoardQueryUseCase {

	BoardQueryDbPort port;
	
	BoardQueryService(BoardQueryDbPort port) {
		this.port = port;
	}
	
	@Override
	public BoardSaveDTO select(Long boardId) {
		return this.select(boardId);
	}
	
	@Override
	public List<BoardSaveDTO> selectList(BoardQueryConditionDTO dto) {
		return this.port.selectList(dto);
	}

	@Override
	public List<BoardHierarchy> selectHierarchy() {
		return this.port.selectHierarchy();
	}

}
