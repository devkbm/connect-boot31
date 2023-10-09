package com.like.system.role.adapter.out.persistence.jpa.entity;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.role.application.port.dto.RoleQueryDTO;
import com.like.system.role.domain.Role;
import com.querydsl.core.BooleanBuilder;

public class RoleMapper {

	public static JpaRole toJpaEntity(Role entity) {
		return new JpaRole(entity.getOrganizationCode()
							   ,entity.getAuthorityCode()
							   ,entity.getDescription());
	}
	
	public static Role toEntity(JpaRole entity) {
		if (entity == null) return null; 
		
		return new Role(entity.getOrganizationCode()
							,entity.getAuthorityCode()
							,entity.getDescription());			
	}
	
	public static BooleanBuilder toPredicate(RoleQueryDTO dto) {
		BooleanBuilder builder = new BooleanBuilder();
		QJpaRole qType = QJpaRole.jpaRole;
			
		builder.and(qType.id.organizationCode.eq(dto.organizationCode()));
		
		if (hasText(dto.roleCode())) {
			builder.and(qType.id.authorityCode.like("%"+dto.roleCode()+"%"));
		}
		
		if (hasText(dto.description())) {
			builder.and(qType.description.like("%"+dto.description()+"%"));
		}			   	
		
		return builder;
	}
}
