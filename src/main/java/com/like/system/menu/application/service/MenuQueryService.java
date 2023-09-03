package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.application.port.in.dto.MenuDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO;
import com.like.system.menu.application.port.in.dto.ResponseMenuHierarchy;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.infra.jparepository.MenuQueryJpaRepository;

@Service
@Transactional(readOnly=true)
public class MenuQueryService {

	private MenuQueryJpaRepository menuJpaRepository;

	public MenuQueryService(MenuQueryJpaRepository menuJpaRepository) {
		this.menuJpaRepository = menuJpaRepository;
	}
		
	public List<MenuGroup> getMenuGroupList(MenuGroupDTO.Search condition) {
		return menuJpaRepository.getMenuGroupList(condition);
	}
	
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return menuJpaRepository.getMenuGroupList(likeMenuGroupName);
	}				
	
	public List<Menu> getMenuList(MenuDTO.Search condition) {
		return menuJpaRepository.getMenuList(condition);
	}
	
	public List<ResponseMenuHierarchy> getMenuHierachy(String organizationCode, String menuGroupCode) {
		List<ResponseMenuHierarchy> rootList = menuJpaRepository.getMenuRootList(organizationCode, menuGroupCode);
		
		return menuJpaRepository.getMenuHierarchyDTO(organizationCode, rootList);
	}	
		
}
