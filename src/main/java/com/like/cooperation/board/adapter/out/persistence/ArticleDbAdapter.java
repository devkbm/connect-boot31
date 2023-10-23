package com.like.cooperation.board.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.adapter.out.persistence.jpa.repository.BoardJpaRepository;
import com.like.cooperation.board.application.port.out.ArticleCommandDbPort;
import com.like.cooperation.board.domain.Article;
import com.like.system.file.application.port.in.FileServerSelectUseCase;
import com.like.system.file.application.port.in.FileServerUploadUseCase;

@Repository
public class ArticleDbAdapter implements ArticleCommandDbPort {
	ArticleJpaRepository repository;
	BoardJpaRepository boardRepository;
	FileServerUploadUseCase uploadUseCase;
	FileServerSelectUseCase fileSelectUseCase;
	
	ArticleDbAdapter(ArticleJpaRepository repository
			        ,BoardJpaRepository boardRepository
			        ,FileServerUploadUseCase uploadUseCase
			        ,FileServerSelectUseCase fileSelectUseCase) {
		this.repository = repository;
		this.boardRepository = boardRepository;
		this.uploadUseCase = uploadUseCase;
		this.fileSelectUseCase = fileSelectUseCase;
	}

	@Override
	public Article select(Long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public void save(Article entity) {
		this.repository.save(entity);
	}	
	
	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
		
	}	
	
}
