package com.like.system.webresource.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.webresource.adapter.out.persistence.jpa.repository.WebResourceJpaRepository;
import com.like.system.webresource.application.port.in.dto.WebResourceQueryConditionDTO;
import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;
import com.like.system.webresource.application.port.out.WebResourceQueryDbPort;

@Repository
@Transactional(readOnly = true)
public class WebResourceQueryDbAdapter implements WebResourceQueryDbPort {

	WebResourceJpaRepository repository;		
	
	public WebResourceQueryDbAdapter(WebResourceJpaRepository repository) {
		this.repository = repository;
	}	

	@Override
	public List<WebResourceSaveDTO> getResourceList(WebResourceQueryConditionDTO condition) {
		return this.repository.findAll(condition.getBooleanBuilder())
							  .stream()
							  .map(e -> WebResourceSaveDTO.toDTO(e))
							  .toList();
	}	

	
}
