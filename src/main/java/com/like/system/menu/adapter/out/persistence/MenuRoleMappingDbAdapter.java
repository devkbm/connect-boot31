package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuRoleMappingJpaRepository;
import com.like.system.menu.application.port.dto.MenuRoleMappingSaveDTO;
import com.like.system.menu.application.port.out.MenuRoleMappingSaveDbPort;
import com.like.system.menu.domain.QMenuRoleMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	@Transactional
	public void save(List<MenuRoleMappingSaveDTO> entityList) {
		
		String orgnizationCode = entityList.get(0).organizationCode();
		String menuGroupCode = entityList.get(0).menuGroupCode();
		String roleCode = entityList.get(0).roleCode();
		
		log.info(orgnizationCode);
		log.info(menuGroupCode);
		log.info(roleCode);
		
		this.queryFactory.delete(qMenuRoleMapping)						 						 
						 .where(qMenuRoleMapping.id.organizationCode.eq(orgnizationCode)
							   ,qMenuRoleMapping.id.menuGroupCode.eq(menuGroupCode) 
							   ,qMenuRoleMapping.id.roleCode.eq(roleCode))												
						 .execute();
		
		
		this.repository.saveAll(entityList.stream().map(e -> e.toEntity()).toList());		
	}

}
