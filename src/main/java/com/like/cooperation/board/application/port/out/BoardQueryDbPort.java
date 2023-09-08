package com.like.cooperation.board.application.port.out;

import java.util.List;

import com.like.cooperation.board.application.dto.BoardHierarchy;
import com.like.cooperation.board.application.dto.BoardQueryConditionDTO;
import com.like.cooperation.board.application.dto.BoardSaveDTO;

public interface BoardQueryDbPort {
	
	BoardSaveDTO select(Long boardId);
	
	List<BoardSaveDTO> selectList(BoardQueryConditionDTO dto);
	
	List<BoardHierarchy> selectHierarchy();
}
