package com.like.cooperation.board.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.application.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.dto.ResponseArticle;
import com.like.cooperation.board.application.port.out.ArticleDeleteDbPort;
import com.like.cooperation.board.application.port.out.ArticleSaveDbPort;
import com.like.cooperation.board.application.port.out.ArticleSelectDbPort;

@Repository
public class ArticleDbAdapter implements ArticleSelectDbPort, ArticleSaveDbPort, ArticleDeleteDbPort {
	ArticleJpaRepository repository;
	
	ArticleDbAdapter(ArticleJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseArticle select(Long articleId) {		
		return null;
	}

	@Override
	public void save(ArticleSaveDTO dto) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Long articleId) {
		// TODO Auto-generated method stub
		
	}
	
}
