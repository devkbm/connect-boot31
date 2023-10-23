package com.like.system.webresource.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.webresource.adapter.out.persistence.jpa.repository.WebResourceJpaRepository;
import com.like.system.webresource.application.port.dto.WebResourceQueryDTO;
import com.like.system.webresource.application.port.out.WebResourceQueryDbPort;
import com.like.system.webresource.domain.WebResource;

@Repository
@Transactional(readOnly = true)
public class WebResourceQueryDbAdapter implements WebResourceQueryDbPort {

	WebResourceJpaRepository repository;		
	
	public WebResourceQueryDbAdapter(WebResourceJpaRepository repository) {
		this.repository = repository;
	}	

	@Override
	public List<WebResource> getResourceList(WebResourceQueryDTO condition) {
		return this.repository.findAll(condition.getBooleanBuilder());
	}	

	
}
