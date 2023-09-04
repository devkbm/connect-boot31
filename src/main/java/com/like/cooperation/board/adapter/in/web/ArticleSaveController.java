package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.cooperation.board.application.port.in.dto.ArticleDTO;
import com.like.cooperation.board.application.service.ArticleCommandService;
import com.like.cooperation.board.domain.Article;
import com.like.system.core.message.MessageUtil;

@Controller
public class ArticleSaveController {	
		
	private ArticleCommandService service;			
		
	public ArticleSaveController(ArticleCommandService service) {
		this.service = service;		
	}	
			
	@PostMapping("/api/grw/board/articletemp")
	@ResponseBody
	public ResponseEntity<?> saveArticleWithMultiPartFile(ArticleDTO.FormArticleByMuiltiPart dto) throws Exception {													
											
		service.saveArticle(dto);											
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
		
	@PostMapping("/api/grw/board/article")
	@ResponseBody
	public ResponseEntity<?> saveArticleJson(@RequestBody @Valid ArticleDTO.FormArticleByJson dto) throws Exception {															
										
		service.saveArticle(dto);											
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
			
	@GetMapping("/api/grw/board/article/hitcnt")
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam Long id,
												 @RequestParam String userId) {								
				
		Article aritlce = service.updateArticleHitCnt(id, userId);			
										
		return toOne(aritlce, String.format("%d건 업데이트 하였습니다.", 1));
	}	
	
}
