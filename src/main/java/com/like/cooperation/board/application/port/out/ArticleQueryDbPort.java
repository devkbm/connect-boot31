package com.like.cooperation.board.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.like.cooperation.board.application.dto.ArticleQueryConditionDTO;
import com.like.cooperation.board.application.dto.ResponseArticle;

public interface ArticleQueryDbPort {

	List<ResponseArticle> getList(ArticleQueryConditionDTO dto);
	
	Slice<ResponseArticle> getAritlceSlice(ArticleQueryConditionDTO dto, Pageable pageable);
}
