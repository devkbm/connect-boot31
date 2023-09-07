package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.application.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.dto.ArticleSaveMultipartDTO;

public interface ArticleSaveDbPort {
	void save(ArticleSaveDTO dto);
	
	void save(ArticleSaveMultipartDTO dto);
}
