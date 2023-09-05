package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.in.ArticleDeleteUseCase;
import com.like.cooperation.board.application.port.out.ArticleDeleteDbPort;

@Service
public class ArticleDeleteService implements ArticleDeleteUseCase {

	ArticleDeleteDbPort port;
	
	ArticleDeleteService(ArticleDeleteDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(Long articleId) {
		this.port.delete(articleId);		
	}

}
