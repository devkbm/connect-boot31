package com.like.system.webresource.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.webresource.domain.QWebResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public record WebResourceQueryDTO(
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
