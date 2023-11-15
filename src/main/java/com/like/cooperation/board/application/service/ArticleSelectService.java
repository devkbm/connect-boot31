package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.port.in.ArticleSelectUseCase;
import com.like.cooperation.board.application.port.out.ArticleCommandDbPort;

@Service
public class ArticleSelectService implements ArticleSelectUseCase {

	ArticleCommandDbPort dbPort;
	
	ArticleSelectService(ArticleCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public ArticleResponseDTO select(Long id) {
		return ArticleResponseDTO.toDTO(this.dbPort.select(id));
	}

}
