package com.like.cooperation.board.application.port.in;

import com.like.cooperation.board.application.port.in.dto.ResponseArticle;

public interface ArticleSelectUseCase {
	ResponseArticle select(Long articleId);
}
