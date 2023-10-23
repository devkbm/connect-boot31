package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.domain.Board;

public interface BoardCommandDbPort {
	Board select(Long id);
	
	void save(Board entity);
	
	void delete(Long id);
}
