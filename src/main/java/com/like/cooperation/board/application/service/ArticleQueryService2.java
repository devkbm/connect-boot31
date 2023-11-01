package com.like.cooperation.board.application.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.application.port.dto.ArticleQueryConditionDTO;
import com.like.cooperation.board.application.port.dto.ResponseArticle;
import com.like.cooperation.board.application.port.in.ArticleQueryUseCase;
import com.like.cooperation.board.application.port.out.ArticleQueryDbPort;

@Service
@Transactional(readOnly = true)
public class ArticleQueryService2 implements ArticleQueryUseCase {

	ArticleQueryDbPort port;
	
	ArticleQueryService2(ArticleQueryDbPort port) {
		this.port = port;
	}
	
	@Override
	public List<ResponseArticle> getList(ArticleQueryConditionDTO dto) {
		return this.port.getList(dto);
	}

	@Override
	public Slice<ResponseArticle> getAritlceSlice(ArticleQueryConditionDTO condition, Pageable pageable) {
		return this.getAritlceSlice(condition, pageable);
	}

	
	
	
}
