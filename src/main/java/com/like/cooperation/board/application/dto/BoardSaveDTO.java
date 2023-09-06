package com.like.cooperation.board.application.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardType;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record BoardSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,
		String organizationCode,
		Long boardId,
		Long boardParentId,
		String boardType,
		@NotEmpty(message="게시판명은 필수 입력사항입니다.")
		String boardName,
		String boardDescription,			
		Boolean useYn,			
		long sequence
		) {
	public Board toEntity(Board parentBoard) {	
		Board entity = Board.builder()
							.parent(parentBoard)
							.boardId(boardId)
							.boardType(BoardType.valueOf(boardType))
							.boardName(boardName)
							.description(boardDescription)
							.useYn(useYn)							
							.build();	
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;					
	}	
	
	public void modifyBoard(Board board, Board parentBoard) {
		board.modifyEntity(parentBoard
				          ,BoardType.valueOf(this.boardType)
				          ,this.boardName
				          ,this.boardDescription					          
				          ,this.useYn
				          ,this.sequence);
		
		board.setAppUrl(clientAppUrl);
	}
	
	public static BoardSaveDTO toDTO(Board entity) {							
		
		if (entity == null)
			return null;
		
		Optional<Board> parent = Optional.ofNullable(entity.getParent());			
		
		return BoardSaveDTO.builder()
					    .createdDt(entity.getCreatedDt())
					    .createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
					    .modifiedDt(entity.getModifiedDt())
					    .modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())
					    .boardId(entity.getBoardId())	
					    .boardParentId(parent.map(Board::getBoardId).orElse(null))
					    .boardType(entity.getBoardType().toString())
					    .boardName(entity.getBoardName())
					    .boardDescription(entity.getDescription())						   						    
					    .useYn(entity.getUseYn())						    
					    .build();	
	}
}
