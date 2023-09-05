package com.like.cooperation.board.application.service;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.port.in.ArticleSaveUseCase;
import com.like.cooperation.board.application.port.out.ArticleSaveDbPort;

@Service
public class ArticleSaveService implements ArticleSaveUseCase {

	ArticleSaveDbPort port;
	
	ArticleSaveService(ArticleSaveDbPort port) {
		this.port = port;
	}
	
	@Override
	public void save(ArticleSaveDTO dto) {
		this.port.save(dto);		
	}

}
