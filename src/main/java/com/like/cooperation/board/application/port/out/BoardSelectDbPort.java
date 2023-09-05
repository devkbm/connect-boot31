package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.application.dto.BoardSaveDTO;

public interface BoardSelectDbPort {
	BoardSaveDTO select(Long boardId);
}
