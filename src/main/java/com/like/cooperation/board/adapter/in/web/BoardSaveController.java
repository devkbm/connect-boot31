package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.dto.BoardDTO;
import com.like.cooperation.board.application.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.in.BoardSaveUseCase;
import com.like.cooperation.board.application.service.BoardCommandService;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardSaveController {
	
	private BoardCommandService boardCommandService;
	BoardSaveUseCase useCase;
		
	public BoardSaveController(BoardCommandService boardCommandService, BoardSaveUseCase useCase) {
		this.boardCommandService = boardCommandService;		
		this.useCase = useCase;
	}			
	/*		
	@PostMapping("/api/grw/board")
	public ResponseEntity<?> saveBoard(@RequestBody @Valid final BoardDTO.FormBoard boardDTO) {												 									
		
		boardCommandService.saveBoard(boardDTO);				
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	*/
	@PostMapping("/api/grw/board")
	public ResponseEntity<?> saveBoard(@RequestBody @Valid final BoardSaveDTO dto) {												 									
		
		useCase.save(dto);				
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
			
}