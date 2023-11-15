package com.like.cooperation.board.application.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.port.in.ArticleQueryUseCase;
import com.like.cooperation.board.application.port.out.ArticleQueryDbPort;

@Transactional(readOnly = true)
@Service
public class ArticleQueryService implements ArticleQueryUseCase {

	ArticleQueryDbPort port;
	
	ArticleQueryService(ArticleQueryDbPort port) {
		this.port = port;
	}
	
	@Override
	public List<ArticleResponseDTO> getList(ArticleQueryDTO dto) {
		return this.port.getList(dto);
	}

	@Override
	public Slice<ArticleResponseDTO> getAritlceSlice(ArticleQueryDTO condition, Pageable pageable) {
		return this.port.getAritlceSlice(condition, pageable);
	}	
	
}
