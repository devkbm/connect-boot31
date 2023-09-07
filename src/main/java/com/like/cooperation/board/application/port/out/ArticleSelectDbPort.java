package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.application.dto.ResponseArticle;
import com.like.cooperation.board.domain.Article;

public interface ArticleSelectDbPort {
	
	Article select(Long articleId);
	
	ResponseArticle selectDTO(Long articleId);
}
