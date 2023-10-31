package com.like.system.term.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.term.application.dto.TermQueryDTO;
import com.like.system.term.application.port.out.TermQueryDbPort;
import com.like.system.term.domain.QTermDictionary;
import com.like.system.term.domain.TermDictionary;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TermQueryDbAdapter implements TermQueryDbPort {
	
	private final QTermDictionary qTermDictionary = QTermDictionary.termDictionary;
		
	private JPAQueryFactory  queryFactory;		

	public TermQueryDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}	
		
	@Override
	public List<TermDictionary> select(TermQueryDTO dto) {									
		return queryFactory.selectFrom(qTermDictionary)
						   .where(dto.getBooleanBuilder())
						   .fetch();
	}


}
