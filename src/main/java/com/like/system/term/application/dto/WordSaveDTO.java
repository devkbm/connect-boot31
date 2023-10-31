package com.like.system.term.application.dto;

import com.like.system.term.domain.WordDictionary;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record WordSaveDTO(
		String organizationCode,
		String clientAppUrl,
		String logicalName,
		String logicalNameEng,
		String physicalName,
		String comment
		) {
	public WordDictionary newEntity() {
		WordDictionary entity = new WordDictionary(logicalName
												  ,logicalNameEng
												  ,physicalName
												  ,comment);
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public void modifyEntity(WordDictionary entity) {
		entity.modify(logicalNameEng, comment);
	}
	
	public static WordSaveDTO toDTO(WordDictionary entity) {
		return WordSaveDTO.builder()
						  .logicalName(entity.getLogicalName())
						  .logicalNameEng(entity.getLogicalNameEng())
						  .physicalName(entity.getPhysicalName())
						  .comment(entity.getComment())
						  .build(); 
	}
}