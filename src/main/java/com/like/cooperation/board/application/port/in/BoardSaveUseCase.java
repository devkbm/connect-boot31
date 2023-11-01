package com.like.cooperation.board.application.port.in;

import com.like.cooperation.board.application.port.dto.BoardSaveDTO;

public interface BoardSaveUseCase {
	void save(BoardSaveDTO dto);
}
