package com.like.system.term.application.port.out;

import com.like.system.term.domain.WordDictionary;

public interface WordCommandDbPort {

	WordDictionary select(String id);
	
	void save(WordDictionary entity);
	
	void delete(String id);
}
