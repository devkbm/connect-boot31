package com.like.system.term.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.dto.WordSaveDTO;
import com.like.system.term.domain.WordDictionary;
import com.like.system.term.domain.WordDictionaryRepository;

@Service
@Transactional
public class WordService {

	private WordDictionaryRepository repository;      
	
    public WordService(WordDictionaryRepository repository) {
    	this.repository = repository;
    }
    
    public List<WordDictionary> getAllList() {
    	return this.repository.findAll();
    }
    
    public WordDictionary get(String id) {
    	return this.repository.findById(id).orElse(null);
    }
    
    public void save(WordSaveDTO dto) {
    	WordDictionary entity = this.getWordDictionary(dto); 
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {			
			dto.modifyEntity(entity);
		}
		
		repository.save(entity);
    }
    
    public void delete(String id) {
    	this.repository.deleteById(id);
    }
    
    private WordDictionary getWordDictionary(WordSaveDTO dto) {
    	return dto.logicalName() == null ? null : repository.findById(dto.logicalName()).orElse(null);
    }
}
