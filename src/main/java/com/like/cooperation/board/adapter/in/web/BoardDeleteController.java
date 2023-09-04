package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.service.BoardCommandService;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardDeleteController {
	
	private BoardCommandService boardCommandService;			
		
	public BoardDeleteController(BoardCommandService boardCommandService) {
		this.boardCommandService = boardCommandService;		
	}			
		
	@DeleteMapping("/api/grw/board/{id}")
	public ResponseEntity<?> delBoard(@PathVariable Long id) {					
												
		boardCommandService.deleteBoard(id);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}			
			
}