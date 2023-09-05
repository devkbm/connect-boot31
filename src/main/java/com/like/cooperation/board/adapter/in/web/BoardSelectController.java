package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.dto.BoardDTO;
import com.like.cooperation.board.application.service.BoardCommandService;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardBookmark;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardSelectController {
	
	private BoardCommandService boardCommandService;			
		
	public BoardSelectController(BoardCommandService boardCommandService) {
		this.boardCommandService = boardCommandService;		
	}
			
	@GetMapping("/api/grw/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id) {				
				
		Board board = boardCommandService.getBoard(id);		
		
		BoardDTO.FormBoard dto = BoardDTO.FormBoard.convertDTO(board);				
							
		return toOne(dto, MessageUtil.getQueryMessage(board != null ? 1 : 0));
	}					
	
	@GetMapping("/api/grw/board/bookmark/{userId}")
	public ResponseEntity<?> getBoardList(@PathVariable String userId) {						
		
		List<BoardBookmark> list = boardCommandService.getBookmarkList(userId); 										
							
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}		
			
}