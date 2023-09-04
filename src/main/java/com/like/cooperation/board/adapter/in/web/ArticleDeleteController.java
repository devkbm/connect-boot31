package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.like.cooperation.board.application.service.ArticleCommandService;
import com.like.cooperation.board.domain.Article;
import com.like.system.core.message.MessageUtil;

@Controller
public class ArticleDeleteController {	
		
	private ArticleCommandService service;			
		
	public ArticleDeleteController(ArticleCommandService service) {
		this.service = service;		
	}	
		
	@DeleteMapping("/api/grw/board/article/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable Long id) {				
				
		service.deleteArticle(id);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
			
	@DeleteMapping("/api/grw/board/article")
	public ResponseEntity<?> deleteArticle(@RequestBody List<Article> articleList) {						
		
		service.deleteArticle(articleList);									
		
		return toList(null, MessageUtil.getDeleteMessage(articleList.size()));
	}			
	
}
