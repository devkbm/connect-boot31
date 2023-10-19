package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.application.port.dto.MenuRoleMappingHierarchyResponseDTO;
import com.like.system.menu.application.port.dto.QMenuRoleMappingHierarchyResponseDTO;
import com.like.system.menu.application.port.out.MenuRoleHierarchySelectDbPort;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuRoleMapping;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuRoleHierarchyDbAdapter implements MenuRoleHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	MenuRoleHierarchyDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuRoleMappingHierarchyResponseDTO> select(String organizationCode, String menuGroupCode, String roleCode) {
		List<MenuRoleMappingHierarchyResponseDTO> rootList = this.getMenuRootList(organizationCode, menuGroupCode, roleCode);
		
		return this.getMenuHierarchyDTO(organizationCode, roleCode, rootList);
	}
		
	private List<MenuRoleMappingHierarchyResponseDTO> getMenuRootList(String organizationCode, String menuGroupCode, String roleCode) {			
		
		JPAQuery<MenuRoleMappingHierarchyResponseDTO> query = queryFactory
				.select(projections(qMenu, qMenuRoleMapping, roleCode))
				.from(qMenu)
				.leftJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.organizationCode.eq(qMenuRoleMapping.id.organizationCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))
					.and(qMenuRoleMapping.id.roleCode.eq(roleCode))
					)
				.where(qMenu.id.menuGroupId.organizationCode.eq(organizationCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
					  ,qMenu.parentMenuCode.isNull()
					  );													
				
		return query.fetch();
	}
	
	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	private List<MenuRoleMappingHierarchyResponseDTO> getMenuHierarchyDTO(String organizationCode, String roleCode, List<MenuRoleMappingHierarchyResponseDTO> list) {
		List<MenuRoleMappingHierarchyResponseDTO> children = null;
		
		for ( MenuRoleMappingHierarchyResponseDTO dto : list ) {			
			
			children = getMenuChildrenList(organizationCode, dto.getMenuGroupCode(), dto.getKey(), roleCode);
			
			if (children.isEmpty()) {
				dto.setIsLeaf(true);
				continue;
			} else {
				dto.setChildren(children);
				dto.setIsLeaf(false);
				
				// 재귀호출
				getMenuHierarchyDTO(organizationCode, roleCode, children);
			}
						
		}
		
		return list;
	}
	
	private List<MenuRoleMappingHierarchyResponseDTO> getMenuChildrenList(String organizationCode, String menuGroupCode, String parentMenuCode, String roleCode) {					
		
		JPAQuery<MenuRoleMappingHierarchyResponseDTO> query = queryFactory			
				.select(projections(qMenu, qMenuRoleMapping, roleCode))
				.from(qMenu)
				.leftJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.organizationCode.eq(qMenuRoleMapping.id.organizationCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))						
					.and(qMenuRoleMapping.id.roleCode.eq(roleCode))
					)									
				.where(qMenu.id.menuGroupId.organizationCode.eq(organizationCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
				      ,qMenu.parentMenuCode.eq(parentMenuCode)
				      );
																		
		return query.fetch();
	}
	
	private QMenuRoleMappingHierarchyResponseDTO projections(QMenu qMenu, QMenuRoleMapping qMenuRoleMapping, String sroleCode) {		
		
		Expression<Boolean> checked = new CaseBuilder()
				.when(qMenuRoleMapping.id.roleCode.isNotNull()).then(true)
				.otherwise(false)
				.as("checked");
				
		// 저장된 Role Code가 없으면 조회 roleCode로 설정
		Expression<String> roleCode = new CaseBuilder()
				.when(qMenuRoleMapping.id.roleCode.isNotNull()).then(qMenuRoleMapping.id.roleCode)
				.otherwise(sroleCode)
				.as("roleCode");
		
		/*
		 *  
			public MenuRoleMappingHierarchyResponseDTO(
			String key, 
			String title,
			boolean checked,
			String menuGroupCode,
			String menuCode,
			String roleCode) {
		 */
		return new QMenuRoleMappingHierarchyResponseDTO(							
				qMenu.id.menuCode,
				qMenu.name,
				checked,
				qMenu.id.menuGroupId.menuGroupCode,
				qMenu.id.menuCode,
				roleCode);
	}

}
