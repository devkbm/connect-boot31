package com.like.cooperation.board.application.port.in;

import com.like.cooperation.board.application.dto.BoardSaveDTO;

public interface BoardSelectUseCase {
	BoardSaveDTO select(Long boardId);
}
