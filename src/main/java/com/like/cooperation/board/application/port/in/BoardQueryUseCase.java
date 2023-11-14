package com.like.cooperation.board.application.port.in;

import java.util.List;

import com.like.cooperation.board.application.port.dto.BoardHierarchy;
import com.like.cooperation.board.application.port.dto.BoardQueryConditionDTO;
import com.like.cooperation.board.application.port.dto.BoardSaveDTO;

public interface BoardQueryUseCase {	
	
	List<BoardSaveDTO> selectList(BoardQueryConditionDTO dto);
	
	List<BoardHierarchy> selectHierarchy();
}
