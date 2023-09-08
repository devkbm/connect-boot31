package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.application.dto.BoardSaveDTO;

public interface BoardCommandDbPort {
	void save(BoardSaveDTO dto);
	
	void delete(Long boardId);
}
