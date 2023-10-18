package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuGroupJpaRepository;
import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuJpaRepository;
import com.like.system.menu.application.port.dto.MenuQueryDTO;
import com.like.system.menu.application.port.dto.MenuSaveDTO;
import com.like.system.menu.application.port.out.MenuDeleteDbPort;
import com.like.system.menu.application.port.out.MenuSaveDbPort;
import com.like.system.menu.application.port.out.MenuSelectDbPort;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupId;
import com.like.system.menu.domain.MenuId;

@Repository
public class MenuDbAdapter implements MenuSelectDbPort, MenuSaveDbPort, MenuDeleteDbPort {
	MenuJpaRepository repository;
	MenuGroupJpaRepository menuGroupRepository;
	
	MenuDbAdapter(MenuJpaRepository repository
				 ,MenuGroupJpaRepository menuGroupRepository) {
		this.repository = repository;
		this.menuGroupRepository = menuGroupRepository;
	}

	@Override
	public MenuSaveDTO select(String organizationCode, String menuGroupCode, String menuCode) {
		Menu entity = this.repository.findById(new MenuId(organizationCode, menuGroupCode, menuCode)).orElse(null); 
						
		return MenuSaveDTO.toDTO(entity);
	}

	@Override
	public List<MenuSaveDTO> selectList(MenuQueryDTO dto) {
		
		return this.repository.findAll(dto.getBooleanBuilder())
							  .stream()
							  .map(e -> MenuSaveDTO.toDTO(e))
							  .toList();							  
	}
	
	@Override
	public void save(MenuSaveDTO dto) {	
		MenuGroup menuGroup = this.menuGroupRepository.findById(new MenuGroupId(dto.organizationCode(), dto.menuGroupCode())).orElse(null);
		Menu parent =  this.repository.findById(new MenuId(dto.organizationCode(), dto.menuGroupCode(), dto.parentMenuCode())).orElse(null);
		this.repository.save(dto.newMenu(menuGroup, parent));
	}

	@Override
	public void delete(String organizationCode, String menuGroupCode, String menuCode) {
		this.repository.deleteById(new MenuId(organizationCode, menuGroupCode, menuCode));		
	}
	
}
