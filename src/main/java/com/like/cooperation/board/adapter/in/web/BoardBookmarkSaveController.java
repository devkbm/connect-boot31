package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.service.BoardCommandService;
import com.like.cooperation.board.domain.BoardBookmark;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardBookmarkSaveController {
	
	private BoardCommandService boardCommandService;			
		
	public BoardBookmarkSaveController(BoardCommandService boardCommandService) {
		this.boardCommandService = boardCommandService;		
	}			
		
	@PostMapping("/api/grw/board/bookmark")
	public ResponseEntity<?> saveBoardBookmark(@RequestBody @Valid final BoardBookmark entity) {
											
		boardCommandService.saveBookmark(entity);				
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
			
}