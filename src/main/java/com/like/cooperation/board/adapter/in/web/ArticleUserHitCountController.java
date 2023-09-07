package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.like.cooperation.board.application.port.in.ArticleUserHitCountUseCase;


@Controller
public class ArticleUserHitCountController {	
					
	ArticleUserHitCountUseCase useCase;	
	
	public ArticleUserHitCountController(ArticleUserHitCountUseCase useCase) {				
		this.useCase = useCase;
	}	
				
	
	@GetMapping("/api/grw/board/article/hitcnt")
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam Long id,
												 @RequestParam String userId) {								
				
		useCase.plusHitCount(id, userId);			
										
		return toOne(null, String.format("%d건 업데이트 하였습니다.", 1));
	}	
	
}
