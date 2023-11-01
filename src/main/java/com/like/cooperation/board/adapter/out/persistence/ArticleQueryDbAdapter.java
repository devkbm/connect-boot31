package com.like.cooperation.board.adapter.out.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.adapter.out.persistence.mybatis.BoardMapper;
import com.like.cooperation.board.application.port.dto.ArticleQueryConditionDTO;
import com.like.cooperation.board.application.port.dto.ResponseArticle;
import com.like.cooperation.board.application.port.out.ArticleQueryDbPort;

@Repository
public class ArticleQueryDbAdapter implements ArticleQueryDbPort {
	
	ArticleJpaRepository repository;
	BoardMapper boardMapper;	
	
	ArticleQueryDbAdapter(ArticleJpaRepository repository
			             ,BoardMapper boardMapper) {
		this.repository = repository;
		this.boardMapper = boardMapper;
	}
	
	@Override
	public List<ResponseArticle> getList(ArticleQueryConditionDTO dto) {

		
		return this.repository.findAll(dto.getBooleanBuilder())
							  .stream()
							  .map(e -> ResponseArticle.toDTO(e))
							  .toList();
	}

	@Override
	public Slice<ResponseArticle> getAritlceSlice(ArticleQueryConditionDTO condition, Pageable pageable) {
		
		Map<String, Object> params = new HashMap<>();	
		params.put("data", condition);
		params.put("pageable", pageable);
		
		List<ResponseArticle> content = boardMapper.getArticleList(params);
		
		// 첨부파일 추가
		for (ResponseArticle dto : content) {
			dto.addFileResponseDTO(repository);
		}			
		
		// 마지막 데이터인지 여부를 확인하기 위해 +1개를 조회한후 데이터 제거
		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}			
		return new SliceImpl<>(content, pageable, hasNext);
	}

}
