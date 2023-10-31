package com.like.system.term.application.port.out;

import com.like.system.term.domain.TermDictionary;

public interface TermCommandDbPort {

	TermDictionary select(String id);
	
	void save(TermDictionary entity);
	
	void delete(String id);
}
