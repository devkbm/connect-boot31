package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.application.port.dto.MenuRoleMappingHierarchyResponseDTO;
import com.like.system.menu.application.port.out.MenuRoleHierarchySelectDbPort;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuRoleMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuRoleHierarchyDbAdapter implements MenuRoleHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	@Override
	public List<MenuRoleMappingHierarchyResponseDTO> select(String organizationCode, String menuGroupCode,
			String roleCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
