package com.like.system.term.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.term.application.port.out.WordQueryDbPort;
import com.like.system.term.domain.WordDictionary;

@Repository
public class WordQueryDbAdpater implements WordQueryDbPort {
	
	WordDictionaryJpaRepository repository;
	
	WordQueryDbAdpater(WordDictionaryJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<WordDictionary> select() {
		return this.repository.findAll();
	}

}
