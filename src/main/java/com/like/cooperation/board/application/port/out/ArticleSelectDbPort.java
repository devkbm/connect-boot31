package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.application.dto.ResponseArticle;

public interface ArticleSelectDbPort {
	ResponseArticle select(Long articleId);
}
