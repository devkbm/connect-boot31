package com.like.system.term.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.system.term.application.port.out.DataDomainCommandDbPort;
import com.like.system.term.domain.DataDomainDictionary;

@Repository
public class DataDomainDbAdapter implements DataDomainCommandDbPort {

	DataDomainDictionaryJpaRepository repository;
	 
	DataDomainDbAdapter(DataDomainDictionaryJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public DataDomainDictionary select(String id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public void save(DataDomainDictionary entity) {
		this.repository.save(entity);
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

}
