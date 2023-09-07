package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.dto.ResponseArticle;
import com.like.cooperation.board.application.port.in.ArticleSelectUseCase;
import com.like.cooperation.board.application.port.out.ArticleSelectDbPort;

@Service
public class ArticleSelectService implements ArticleSelectUseCase {

	ArticleSelectDbPort port;
	
	ArticleSelectService(ArticleSelectDbPort port) {
		this.port = port;
	}
	
	@Override
	public ResponseArticle select(Long articleId) {
		return this.port.selectDTO(articleId);
	}

}
