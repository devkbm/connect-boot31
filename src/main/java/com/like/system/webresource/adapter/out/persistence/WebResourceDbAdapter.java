package com.like.system.webresource.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.system.webresource.adapter.out.persistence.jpa.repository.WebResourceJpaRepository;
import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;
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
	public WebResourceSaveDTO select(String webResourceId) {
		WebResource entity = this.repository.findById(webResourceId).orElse(null);
		return WebResourceSaveDTO.toDTO(entity);
	}

	@Override
	public void save(WebResourceSaveDTO dto) {
		this.repository.save(dto.toEntity());		
	}

	@Override
	public void delete(String webResourceId) {
		this.repository.deleteById(webResourceId);
	}
	
}
