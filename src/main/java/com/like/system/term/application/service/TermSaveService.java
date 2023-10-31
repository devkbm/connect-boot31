package com.like.system.term.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.dto.TermSaveDTO;
import com.like.system.term.application.port.in.TermSaveUseCase;
import com.like.system.term.application.port.out.DataDomainCommandDbPort;
import com.like.system.term.application.port.out.TermCommandDbPort;
import com.like.system.term.application.port.out.WordCommandDbPort;
import com.like.system.term.domain.DataDomainDictionary;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.WordDictionary;

@Transactional
@Service
public class TermSaveService implements TermSaveUseCase {

	TermCommandDbPort dbPort;
	WordCommandDbPort wordDbPort;
	DataDomainCommandDbPort dataDomainDbPort;
	
	TermSaveService(TermCommandDbPort dbPort,
					WordCommandDbPort wordDbPort,
					DataDomainCommandDbPort dataDomainDbPort) {
		this.dbPort = dbPort;
		this.wordDbPort = wordDbPort;
		this.dataDomainDbPort = dataDomainDbPort;
	}
	
	@Override
	public void save(TermSaveDTO dto) {
		TermDictionary entity = dto.termId() == null ? null : dbPort.select(dto.termId());
		
		if (entity == null) {
			entity = this.createEntity(dto);									
		} else {						  				
			dto.modifyEntity(entity, this.getDataDomainDictionary(dto));
		}
		
		this.dbPort.save(entity);
	
	}
	
	private TermDictionary createEntity(TermSaveDTO dto) {
		TermDictionary entity = null;
		DataDomainDictionary dataDomain = this.getDataDomainDictionary(dto);
				
		if (dto.term().size() == 1) {				
			entity = dto.newEntity(this.getWordDictionary(dto.term().get(0)), dataDomain);
		} else if (dto.term().size() > 1) {				
			entity = dto.newEntity(this.getWordDictionary(dto.term()), dataDomain);
		}
		
		return entity;
	}
	
	private DataDomainDictionary getDataDomainDictionary(TermSaveDTO dto) {
		if (dto.dataDomainId() == null) return null;
		
		return dataDomainDbPort.select(dto.dataDomainId());
	}
	
	private WordDictionary getWordDictionary(String id) {
		return wordDbPort.select(id);
	}
	
	private List<WordDictionary> getWordDictionary(List<String> ids) {
		// List<String> 순번대로 조회되지 않아서 수정
		// return wordDictionaryRepository.findAllById(ids);
		
		List<WordDictionary> list = new ArrayList<>(ids.size());
		
		for (String id : ids) {
			list.add(this.getWordDictionary(id));
		}
		return list;
	}

}
