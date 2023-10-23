package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuRoleMappingJpaRepository;
import com.like.system.menu.application.port.out.MenuRoleMappingSaveDbPort;
import com.like.system.menu.domain.MenuRoleMapping;
import com.like.system.menu.domain.QMenuRoleMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuRoleMappingDbAdapter implements MenuRoleMappingSaveDbPort {

	JPAQueryFactory queryFactory;
	MenuRoleMappingJpaRepository repository;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	MenuRoleMappingDbAdapter(MenuRoleMappingJpaRepository repository
							,JPAQueryFactory queryFactory) {
		this.repository = repository;
		this.queryFactory = queryFactory;
	}
	
	@Override	
	public void save(List<MenuRoleMapping> entityList) {									
		this.repository.saveAll(entityList);		
	}

	@Override
	public void clear(String orgnizationCode, String menuGroupCode, String roleCode) {
		this.queryFactory.delete(qMenuRoleMapping)						 						 
						 .where(qMenuRoleMapping.id.organizationCode.eq(orgnizationCode)
							   ,qMenuRoleMapping.id.menuGroupCode.eq(menuGroupCode) 
							   ,qMenuRoleMapping.id.roleCode.eq(roleCode))												
						 .execute();
	}

}
