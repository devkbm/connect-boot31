package com.like.system.menu.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.application.port.in.dto.MenuDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO;
import com.like.system.menu.application.port.in.dto.QResponseMenuHierarchy;
import com.like.system.menu.application.port.in.dto.ResponseMenuHierarchy;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuQueryRepository;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuGroup;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuQueryJpaRepository implements MenuQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;	
	private final QMenu qMenu = QMenu.menu;	
	
	public MenuQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuGroup> getMenuGroupList(MenuGroupDTO.Search condition) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(qMenuGroup.name.like(likeMenuGroupName+"%"))
				.fetch();
	}

	@Override
	public List<Menu> getMenuList(MenuDTO.Search condition) {
		return queryFactory
				.selectFrom(qMenu)
					.innerJoin(qMenu.menuGroup, qMenuGroup)
					.fetchJoin()
				.where(condition.getBooleanBuilder())				
				.fetch();
	}
	
	public List<ResponseMenuHierarchy> getMenuRootList(String organizationCode, String menuGroupCode) {			
						
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
			
	public List<ResponseMenuHierarchy> getMenuChildrenList(String organizationCode, String menuGroupCode, String parentMenuCode) {					
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
	

	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	public List<ResponseMenuHierarchy> getMenuHierarchyDTO(String organizationCode, List<ResponseMenuHierarchy> list) {
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
