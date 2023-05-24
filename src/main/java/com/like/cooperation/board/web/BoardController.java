package com.like.cooperation.board.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.boundary.BoardDTO;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardBookmark;
import com.like.cooperation.board.service.BoardCommandService;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardController {
	
	private BoardCommandService boardCommandService;			
		
	public BoardController(BoardCommandService boardCommandService) {
		this.boardCommandService = boardCommandService;		
	}
			
	@GetMapping("/api/grw/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id) {				
				
		Board board = boardCommandService.getBoard(id);		
		
		BoardDTO.FormBoard dto = BoardDTO.FormBoard.convertDTO(board);				
							
		return toOne(dto, MessageUtil.getQueryMessage(board != null ? 1 : 0));
	}	
			
	@PostMapping("/api/grw/board")
	public ResponseEntity<?> saveBoard(@RequestBody @Valid final BoardDTO.FormBoard boardDTO) {												 									
		
		boardCommandService.saveBoard(boardDTO);				
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
		
	@DeleteMapping("/api/grw/board/{id}")
	public ResponseEntity<?> delBoard(@PathVariable Long id) {					
												
		boardCommandService.deleteBoard(id);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}		
	
	@GetMapping("/api/grw/board/bookmark/{userId}")
	public ResponseEntity<?> getBoardList(@PathVariable String userId) {						
		
		List<BoardBookmark> list = boardCommandService.getBookmarkList(userId); 										
							
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
		
	@PostMapping("/api/grw/board/bookmark")
	public ResponseEntity<?> saveBoardBookmark(@RequestBody @Valid final BoardBookmark entity) {
											
		boardCommandService.saveBookmark(entity);				
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/grw/board/bookmark/{id}")
	public ResponseEntity<?> delBoardBookmark(@PathVariable Long id) {					
												
		boardCommandService.deleteBookmark(id);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
			
}