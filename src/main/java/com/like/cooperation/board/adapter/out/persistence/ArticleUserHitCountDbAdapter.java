package com.like.cooperation.board.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleUserHitCountJpaRepository;
import com.like.cooperation.board.application.port.out.ArticleUserHitCountDbPort;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleUserHitCount;
import com.like.cooperation.board.domain.ArticleUserHitCountId;

@Repository
public class ArticleUserHitCountDbAdapter implements ArticleUserHitCountDbPort {

	ArticleUserHitCountJpaRepository repository;
	
	ArticleUserHitCountDbAdapter(ArticleUserHitCountJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void plusHitCount(Article article, String userId) {
		
		ArticleUserHitCount isRead = this.repository.findById(new ArticleUserHitCountId(article, userId))
				                            .orElse(new ArticleUserHitCount(article, userId));

		isRead.updateHitCnt();

		this.repository.save(isRead);
	}

}
