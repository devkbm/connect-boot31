package com.like.system.term.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.term.application.port.out.DataDomainQueryDbPort;
import com.like.system.term.domain.DataDomainDictionary;

@Repository
public class DataDomainQueryDbAdapter implements DataDomainQueryDbPort {

	DataDomainDictionaryJpaRepository repository;
	
	DataDomainQueryDbAdapter(DataDomainDictionaryJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<DataDomainDictionary> select() {
		return this.repository.findAll();
	}

}
