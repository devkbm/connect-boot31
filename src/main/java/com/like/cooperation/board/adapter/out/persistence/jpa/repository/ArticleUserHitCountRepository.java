package com.like.cooperation.board.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.cooperation.board.domain.ArticleUserHitCount;
import com.like.cooperation.board.domain.ArticleUserHitCountId;

@Repository
public interface ArticleUserHitCountRepository extends JpaRepository<ArticleUserHitCount, ArticleUserHitCountId> {
	
}


/*
queryFactory
				.selectFrom(qArticleCheck)
				.where(qArticleCheck.createdBy.eq(userId)
				  .and(qArticleCheck.article.pkArticle.eq(fkarticle)))
				.fetchOne();			
	}
*/