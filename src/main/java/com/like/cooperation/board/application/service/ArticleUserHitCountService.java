package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.in.ArticleUserHitCountUseCase;
import com.like.cooperation.board.application.port.out.ArticleSelectDbPort;
import com.like.cooperation.board.application.port.out.ArticleUserHitCountDbPort;
import com.like.cooperation.board.domain.Article;

@Service
public class ArticleUserHitCountService implements ArticleUserHitCountUseCase {
	
	ArticleUserHitCountDbPort port;
	ArticleSelectDbPort articlePort;
	
	ArticleUserHitCountService(ArticleUserHitCountDbPort port
							  ,ArticleSelectDbPort articlePort) {
		this.port = port;				
		this.articlePort = articlePort;
	}
	
	@Override
	public void plusHitCount(Long articleId, String userId) {
		Article article = this.articlePort.select(articleId);
		
		article.updateHitCnt();
		
		this.port.plusHitCount(article, userId);
	}	

}
