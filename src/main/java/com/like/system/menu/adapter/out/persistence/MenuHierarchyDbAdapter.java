package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.application.port.dto.ResponseMenuHierarchy;
import com.like.system.menu.application.port.in.dto.QResponseMenuHierarchy;
import com.like.system.menu.application.port.out.MenuHierarchySelectDbPort;
import com.like.system.menu.domain.QMenu;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuHierarchyDbAdapter implements MenuHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	
	MenuHierarchyDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<ResponseMenuHierarchy> select(String organizationCode, String menuGroupCode) {
				
		List<ResponseMenuHierarchy> rootList = this.getMenuRootList(organizationCode, menuGroupCode);
		
		return this.getMenuHierarchyDTO(organizationCode, rootList);
	}
	
	private List<ResponseMenuHierarchy> getMenuRootList(String organizationCode, String menuGroupCode) {			
		
		// menuGroupCode, organizationCode 반대로 동작 확인 필요
		JPAQuery<ResponseMenuHierarchy> query = queryFactory
				.select(projections(qMenu))
				.from(qMenu)								
				.where(qMenu.id.menuGroupId.organizationCode.eq(organizationCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
					  ,qMenu.parentMenuCode.isNull()
					  );													
				
		return query.fetch();
	}
	
	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	private List<ResponseMenuHierarchy> getMenuHierarchyDTO(String organizationCode, List<ResponseMenuHierarchy> list) {
		List<ResponseMenuHierarchy> children = null;
		
		for ( ResponseMenuHierarchy dto : list ) {			
			
			children = getMenuChildrenList(organizationCode, dto.getMenuGroupCode(), dto.getKey());
			
			if (children.isEmpty()) {
				dto.setIsLeaf(true);
				continue;
			} else {
				dto.setChildren(children);
				dto.setIsLeaf(false);
				
				// 재귀호출
				getMenuHierarchyDTO(organizationCode, children);
			}
						
		}
		
		return list;
	}
	
	private List<ResponseMenuHierarchy> getMenuChildrenList(String organizationCode, String menuGroupCode, String parentMenuCode) {					
		/*
		Expression<Boolean> isLeaf = new CaseBuilder()										
											.when(qMenu.parent.menuCode.isNotNull()).then(true)
											.otherwise(false).as("isLeaf");
		*/
		// menuGroupCode, organizationCode 반대로 동작 확인 필요
		JPAQuery<ResponseMenuHierarchy> query = queryFactory			
				.select(projections(qMenu))
				.from(qMenu)									
				.where(qMenu.id.menuGroupId.organizationCode.eq(organizationCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
				      ,qMenu.parentMenuCode.eq(parentMenuCode)
				      );
																		
		return query.fetch();
	}
	
	private QResponseMenuHierarchy projections(QMenu qMenu) {		
		
		/*
		 *  ResponseMenuHierarchy(String menuGroupCode, String key, String title, String parentMenuCode,
			MenuType menuType, Long sequence, Long level, String url) {		
		*/
		return new QResponseMenuHierarchy(qMenu.menuGroup.id.menuGroupCode
										 ,qMenu.id.menuCode
										 ,qMenu.name
										 ,qMenu.parentMenuCode
										 ,qMenu.type
										 ,qMenu.sequence
										 ,qMenu.level
										 ,qMenu.appUrl);
	}

}
