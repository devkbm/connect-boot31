package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.application.port.out.SystemUserMenuGroupSelectDbPort;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.QMenuGroup;
import com.like.system.menu.domain.QMenuRoleMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SystemUserMenuGroupDbAdapter implements SystemUserMenuGroupSelectDbPort {

	JPAQueryFactory queryFactory;	
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	SystemUserMenuGroupDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuGroup> select(String organizationCode, List<String> roleCodes) {		
		return this.queryFactory.select(qMenuGroup).distinct()
				                .from(qMenuGroup)
				                .innerJoin(qMenuRoleMapping)
				                .on(qMenuGroup.id.organizationCode.eq(qMenuRoleMapping.id.organizationCode),
				                	qMenuGroup.id.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
				                .where(qMenuRoleMapping.id.roleCode.in(roleCodes))
				                .fetch();				               
	}

}
