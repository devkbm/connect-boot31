package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.like.cooperation.board.application.dto.ResponseArticle;
import com.like.cooperation.board.application.port.in.ArticleSelectUseCase;
import com.like.system.core.message.MessageUtil;

@Controller
public class ArticleSelectController {	
			
	ArticleSelectUseCase useCase;
	
	public ArticleSelectController(ArticleSelectUseCase useCase) {		
		this.useCase = useCase;
	}		
	
	@GetMapping("/api/grw/board/article/{id}")
	public ResponseEntity<?> getArticle(@PathVariable Long id, HttpSession session) {						
					
		ResponseArticle response =  useCase.select(id);				
		
		return toOne(response, MessageUtil.getQueryMessage(response == null ? 0 : 1));
	}
	
}
