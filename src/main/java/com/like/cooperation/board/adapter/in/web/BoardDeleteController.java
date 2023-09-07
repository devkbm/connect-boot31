package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.port.in.BoardDeleteUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardDeleteController {
		
	BoardDeleteUseCase useCase;
		
	public BoardDeleteController(BoardDeleteUseCase useCase) {		
		this.useCase = useCase;
	}			
	
	@DeleteMapping("/api/grw/board/{id}")
	public ResponseEntity<?> delBoard(@PathVariable Long id) {					
												
		useCase.delete(id);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
			
}