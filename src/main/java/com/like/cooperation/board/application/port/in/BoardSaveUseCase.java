package com.like.cooperation.board.application.port.in;

import com.like.cooperation.board.application.port.in.dto.BoardSaveDTO;

public interface BoardSaveUseCase {
	void save(BoardSaveDTO dto);
}
