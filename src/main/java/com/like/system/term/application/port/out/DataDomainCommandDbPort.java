package com.like.system.term.application.port.out;

import com.like.system.term.domain.DataDomainDictionary;

public interface DataDomainCommandDbPort {
	DataDomainDictionary select(String id);
	
	void save(DataDomainDictionary entity);
	
	void delete(String id);
}
