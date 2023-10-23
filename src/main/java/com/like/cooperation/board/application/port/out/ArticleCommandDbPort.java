package com.like.cooperation.board.application.port.out;

import com.like.cooperation.board.domain.Article;

public interface ArticleCommandDbPort {
	Article select(Long id);
	
	void save(Article entity);	
	
	void delete(Long id);
}
