package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.application.dto.BoardSaveDTO;

public interface BoardSaveDbPort {
	void save(BoardSaveDTO dto);
}
