package com.like.system.authority.adapter.out.persistence;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.authority.application.port.in.dto.AuthorityQueryRequestDTO;
import com.like.system.authority.domain.Authority;
import com.querydsl.core.BooleanBuilder;

public class AuthorityMapper {

	public static JpaAuthority toJpaEntity(Authority entity) {
		return new JpaAuthority(entity.getOrganizationCode()
							   ,entity.getAuthorityCode()
							   ,entity.getDescription());
	}
	
	public static Authority toEntity(JpaAuthority entity) {
		if (entity == null) return null; 
		
		return new Authority(entity.getOrganizationCode()
							,entity.getAuthorityCode()
							,entity.getDescription());			
	}
	
	public static BooleanBuilder toPredicate(AuthorityQueryRequestDTO dto) {
		BooleanBuilder builder = new BooleanBuilder();
		QJpaAuthority qType = QJpaAuthority.jpaAuthority;
			
		builder.and(qType.id.organizationCode.eq(dto.organizationCode()));
		
		if (hasText(dto.authorityCode())) {
			builder.and(qType.id.authorityCode.like("%"+dto.authorityCode()+"%"));
		}
		
		if (hasText(dto.description())) {
			builder.and(qType.description.like("%"+dto.description()+"%"));
		}			   	
		
		return builder;
	}
}
