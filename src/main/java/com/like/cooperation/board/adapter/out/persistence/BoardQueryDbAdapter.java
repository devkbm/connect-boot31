package com.like.cooperation.board.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.application.port.dto.QBoardHierarchy;
import com.like.cooperation.board.application.port.dto.BoardHierarchy;
import com.like.cooperation.board.application.port.dto.BoardQueryDTO;
import com.like.cooperation.board.application.port.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.out.BoardQueryDbPort;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.QBoard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BoardQueryDbAdapter implements BoardQueryDbPort {

	private JPAQueryFactory queryFactory;
	private final QBoard qBoard = QBoard.board;
	
	public BoardQueryDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}	
	
	@Override
	public List<BoardSaveDTO> selectList(BoardQueryDTO dto) {
		List<Board> list = queryFactory.selectFrom(qBoard)
									   .where(dto.getBooleanBuilder())
									   .fetch(); 
		
		return list.stream()
					.map(e -> BoardSaveDTO.toDTO(e))
					.toList();
				
	}

	@Override
	public List<BoardHierarchy> selectHierarchy() {
		List<BoardHierarchy> rootList = getBoardHierarchyRootList();
		
		List<BoardHierarchy> rtn =  setLinkBoardHierarchy(rootList);
		
		return rtn;
	}

	private List<BoardHierarchy> setLinkBoardHierarchy(List<BoardHierarchy> list) {
		List<BoardHierarchy> children = null;
		
		for ( BoardHierarchy dto : list) {
			
			children = getBoardHierarchyChildrenList(dto.getKey());
			
			if (children.isEmpty()) {	// leaf 노드이면 다음 리스트 검색
				dto.setLeaf(true);
				continue;
			} else {
				
				dto.setChildren(children);
				dto.setLeaf(false);
				
				setLinkBoardHierarchy(children);
			}
		}
		
		return list;
	}
	
	private List<BoardHierarchy> getBoardHierarchyRootList() {									
		
		/*Expression<Boolean> isLeaf = new CaseBuilder()
										.when(qBoard.ppkBoard.isNotNull()).then(true)
										.otherwise(false).as("leaf");*/	
		
		JPAQuery<BoardHierarchy> query = queryFactory
				.select(new QBoardHierarchy(qBoard.boardId, qBoard.parent.boardId, qBoard.boardType
										   ,qBoard.boardName, qBoard.description))
				.from(qBoard)
				.where(qBoard.isRootNode());
													
						
		return query.fetch();	
	}
	
	private List<BoardHierarchy> getBoardHierarchyChildrenList(Long boardParentId) {
		
		JPAQuery<BoardHierarchy> query = queryFactory
				.select(new QBoardHierarchy(qBoard.boardId, qBoard.parent.boardId, qBoard.boardType
						   				   ,qBoard.boardName, qBoard.description))
				.from(qBoard)
				.where(qBoard.parent.boardId.eq(boardParentId));								
		
		return query.fetch();
		
	}

}
