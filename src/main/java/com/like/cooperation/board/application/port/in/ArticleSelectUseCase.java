package com.like.cooperation.board.application.port.in;

import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;

public interface ArticleSelectUseCase {
	ArticleResponseDTO select(Long id);
}
