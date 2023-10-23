package com.like.system.webresource.application.port.dto;

import java.time.LocalDateTime;

import com.like.system.webresource.domain.WebResource;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record WebResourceSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,
		String organizationCode,
		@NotEmpty
		String resourceId,
		@NotEmpty
		String resourceName,
		String resourceType,
		@NotEmpty
		String url,
		String description
		) {

	public static WebResourceSaveDTO toDTO(WebResource entity) {								
		return WebResourceSaveDTO
					.builder()
				    .createdDt(entity.getCreatedDt())	
				    .createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
				    .modifiedDt(entity.getModifiedDt())
				    .modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())
				    .resourceId(entity.getId())
				    .resourceName(entity.getName())
				    .resourceType(entity.getType())
				    .url(entity.getUrl())
				    .description(entity.getDescription())
				    .build();
	}
	
	public WebResource toEntity() {
		WebResource entity = WebResource.builder()
										.resourceId(this.resourceId)
										.resourceName(this.resourceName)
										.resourceType(this.resourceType)
										.url(this.url)
										.description(this.description)
										.build();	
		entity.setAppUrl(clientAppUrl);
		
		return entity;	
	}	
}
