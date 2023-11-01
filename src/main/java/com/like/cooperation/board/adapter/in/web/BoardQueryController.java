package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.port.dto.BoardQueryConditionDTO;
import com.like.cooperation.board.application.port.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.dto.BoardTypeDTO;
import com.like.cooperation.board.application.port.in.BoardQueryUseCase;
import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.dto.HtmlSelectOptionable;
import com.like.system.core.message.MessageUtil;

@RestController
public class BoardQueryController {
	
	BoardQueryUseCase useCase;
	
	public BoardQueryController(BoardQueryUseCase useCase) {		
		this.useCase = useCase;
	}
	
	@GetMapping("/api/grw/board/boardType")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionable.fromEnum(BoardTypeDTO.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = useCase.selectHierarchy();				 			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/grw/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id) {				
								
		BoardSaveDTO dto = this.useCase.select(id);				
							
		return toOne(dto, MessageUtil.getQueryMessage(dto != null ? 1 : 0));
	}		
	
	@GetMapping("/api/grw/board")
	public ResponseEntity<?> getBoardList(BoardQueryConditionDTO dto) {
		List<BoardSaveDTO> dtoList = useCase.selectList(dto); 												
				
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}	
	

}
