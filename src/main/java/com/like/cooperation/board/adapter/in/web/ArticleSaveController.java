package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.cooperation.board.application.port.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.port.dto.ArticleSaveMultipartDTO;
import com.like.cooperation.board.application.port.in.ArticleSaveUseCase;
import com.like.system.core.message.MessageUtil;

@Controller
public class ArticleSaveController {	
					
	ArticleSaveUseCase useCase;	
	
	public ArticleSaveController(ArticleSaveUseCase useCase) {				
		this.useCase = useCase;
	}	
			
	@PostMapping("/api/grw/board/article_multipart")
	@ResponseBody
	public ResponseEntity<?> saveArticleWithMultiPartFile(ArticleSaveMultipartDTO dto) throws Exception {													
											
		useCase.save(dto);											
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
	
	@PostMapping("/api/grw/board/article")
	@ResponseBody
	public ResponseEntity<?> saveArticleJson(@RequestBody @Valid ArticleSaveDTO dto) throws Exception {															
										
		useCase.save(dto);											
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
	
}
