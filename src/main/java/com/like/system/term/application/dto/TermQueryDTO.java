package com.like.system.term.application.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.term.domain.QTermDictionary;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public record TermQueryDTO(			
		String term,
		String dataDomainName
		) {
			
	private static final QTermDictionary qType = QTermDictionary.termDictionary;
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
								
		builder.and(likeTerm(this.term))
			   .and(likeDataDomain(this.dataDomainName));
								
		return builder;
	}
	
	private BooleanExpression likeTerm(String term) {
		return hasText(term) ? qType.term.like("%"+this.term+"%") : null;					
	}
	
	private BooleanExpression likeDataDomain(String dataDomain) {			
		return hasText(dataDomain) ? qType.dataDomain.domainName.like("%"+this.dataDomainName+"%") : null;					
	}		
	
}