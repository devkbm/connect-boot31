package com.like.cooperation.board.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.BoardJpaRepository;
import com.like.cooperation.board.application.dto.BoardDTO;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardBookmark;
import com.like.cooperation.board.domain.BoardBookmarkRepository;


@Service
@Transactional
public class BoardCommandService {
	
	private BoardJpaRepository boardRepository;
	private BoardBookmarkRepository bookmarkRepository;		
	     
	public BoardCommandService(BoardJpaRepository boardRepository
							  ,BoardBookmarkRepository bookmarkRepository) {
		this.boardRepository = boardRepository;
		this.bookmarkRepository = bookmarkRepository;		
	}

	public Board getBoard(Long id) {
    	return boardRepository.findById(id).orElse(null);
    }
	
	public void saveBoard(BoardDTO.FormBoard dto) {			
		Board board = null;			
		Board parentBoard = dto.boardParentId() != null ? boardRepository.findById(dto.boardParentId()).orElse(null) : null;			
																
		if (dto.boardId() == null) {
			board = dto.newBoard(parentBoard);
		} else {
			board = boardRepository.findById(dto.boardId()).orElse(null);
			dto.modifyBoard(board, parentBoard);			
		}			
		
		boardRepository.save(board);
	}
	
	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}			
	
	public void deleteBoard(Board board) {
		boardRepository.delete(board);
	}
	
	public List<BoardBookmark> getBookmarkList(String userId) {									
		return bookmarkRepository.findByUserIdOrderBySeqAsc(userId);
	}	
	
	public void saveBookmark(BoardBookmark entity) {
		bookmarkRepository.save(entity);
	}
	
	public void deleteBookmark(Long id) {		
		
		bookmarkRepository.deleteById(id);
	}							
		
}