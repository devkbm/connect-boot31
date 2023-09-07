package com.like.cooperation.board.application.port.in;

import com.like.cooperation.board.application.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.dto.ArticleSaveMultipartDTO;

public interface ArticleSaveUseCase {
	void save(ArticleSaveDTO dto);
	
	void save(ArticleSaveMultipartDTO dto);
		
}
