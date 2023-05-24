package com.like.system.webresource.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;

import com.like.system.webresource.domain.QWebResource;
import com.like.system.webresource.domain.WebResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class WebResourceDTO {
	
	public record SearchWebResource(
			String resourceCode,
			String resourceName,
			String resourceType,
			String url,
			String description
			) {
		
		private static final QWebResource qType = QWebResource.webResource;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeResourceCode(this.resourceCode))
				.and(likeResourceName(this.resourceName))
				.and(likeResourceType(this.resourceType))
				.and(likeUrl(this.url))
				.and(likeDescription(this.description));									
											
			return builder;
		}
		
		private BooleanExpression likeResourceCode(String resourceCode) {
			return hasText(resourceCode) ? qType.id.like("%"+resourceCode+"%") : null;					
		}
		
		private BooleanExpression likeResourceName(String resourceName) {
			return hasText(resourceName) ? qType.name.like("%"+resourceName+"%") : null;					
		}
		
		private BooleanExpression likeResourceType(String resourceType) {
			return hasText(resourceName) ? qType.type.like("%"+resourceType+"%") : null;			
		}
		
		private BooleanExpression likeUrl(String url) {
			return hasText(url) ? qType.url.like("%"+url+"%") : null;					
		}
		
		private BooleanExpression likeDescription(String description) {
			return hasText(description) ? qType.description.like("%"+description+"%") : null;
		}
		
	}	
	
	@Builder
	public static record Form(
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
		
		public static Form convertDTO(WebResource entity) {								
			return Form.builder()
					   .createdDt(entity.getCreatedDt())	
					   .createdBy(entity.getCreatedBy().getLoggedUser())
					   .modifiedDt(entity.getCreatedDt())
					   .modifiedBy(entity.getModifiedBy().getLoggedUser())
					   .resourceId(entity.getId())
					   .resourceName(entity.getName())
					   .resourceType(entity.getType())
					   .url(entity.getUrl())
					   .description(entity.getDescription())
					   .build();
		}
		
		public WebResource newEntity() {
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
		
		public void modifyEntity(WebResource entity) {
			entity.modifyEntity(resourceName
							   ,resourceType
							   ,url
							   ,description);
			
			entity.setAppUrl(clientAppUrl);
		}			
		
	}
	
	
}
