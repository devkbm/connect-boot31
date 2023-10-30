package com.like.system.term.application.dto;

import java.util.ArrayList;
import java.util.List;

import com.like.system.term.domain.DataDomainDictionary;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.WordDictionary;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record TermSaveDTO(
		String organizationCode,
		String clientAppUrl,
		String termId,
		String system,
		@NotEmpty(message = "용어는 필수 입력 값입니다.")
		List<String> term,
		String termEng,
		String columnName,
		String dataDomainId,
		String dataDomainName,
		String description,
		String comment
		) {
	
	private static List<String> toList(String term) {
		String[] terms = term.split("_");
		List<String> list = new ArrayList<String>(terms.length);
		
		for (int i=0; i<terms.length; i++) {				
			list.add(terms[i]);
		}
		
		return list;
	}		
	
	public TermDictionary newEntity(WordDictionary word, DataDomainDictionary dataDomain) {												
		return TermDictionary.of(system, word, termEng, dataDomain, description, comment);
	}
	
	public TermDictionary newEntity(List<WordDictionary> word, DataDomainDictionary dataDomain) {
		return TermDictionary.of(system, word, termEng, dataDomain, description, comment);
	}
	
	
	public void modifyEntity(TermDictionary entity, DataDomainDictionary dataDomain) {
		
		entity.modifyEntity(termEng					           
				           ,dataDomain
				           ,description
				           ,comment);			
		
	}
	
	public static TermSaveDTO convert(TermDictionary entity) {
		return TermSaveDTO.builder()						   
					   .termId(entity.getId())
					   .system(entity.getSystem())						   
					   .term(toList(entity.getTerm()))
					   .termEng(entity.getTermEng())
					   .columnName(entity.getColumnName())
					   .dataDomainId(entity.getDataDomain().getId())
					   .dataDomainName(entity.getDataDomain().getDomainName())
					   .description(entity.getDescription())
					   .comment(entity.getComment())
					   .build();						   
	}
		
}