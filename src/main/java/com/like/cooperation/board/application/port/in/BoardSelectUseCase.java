package com.like.cooperation.board.application.port.in;

import com.like.cooperation.board.application.port.in.dto.BoardSaveDTO;

public interface BoardSelectUseCase {
	BoardSaveDTO select(Long boardId);
}
