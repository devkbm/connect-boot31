package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.application.dto.ArticleSaveDTO;

public interface ArticleSaveDbPort {
	void save(ArticleSaveDTO dto);
}
