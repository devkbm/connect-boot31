package com.like.system.webresource.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.system.webresource.adapter.out.persistence.jpa.repository.WebResourceJpaRepository;
import com.like.system.webresource.application.port.out.WebResourceDbDeletePort;
import com.like.system.webresource.application.port.out.WebResourceDbSavePort;
import com.like.system.webresource.application.port.out.WebResourceDbSelectPort;
import com.like.system.webresource.domain.WebResource;

@Repository
public class WebResourceDbAdapter implements WebResourceDbSelectPort, WebResourceDbSavePort, WebResourceDbDeletePort {

	WebResourceJpaRepository repository;
	
	WebResourceDbAdapter(WebResourceJpaRepository repository) {
		this.repository = repository;
	}
	 
	@Override
	public WebResource select(String id) {		
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public void save(WebResource entity) {
		this.repository.save(entity);		
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}
	
}
