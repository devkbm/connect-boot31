package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuGroupJpaRepository;
import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuJpaRepository;
import com.like.system.menu.application.port.dto.MenuQueryDTO;
import com.like.system.menu.application.port.out.MenuDeleteDbPort;
import com.like.system.menu.application.port.out.MenuSaveDbPort;
import com.like.system.menu.application.port.out.MenuSelectDbPort;
import com.like.system.menu.domain.Menu;
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
	public Menu select(String organizationCode, String menuGroupCode, String menuCode) {		
						
		return this.repository.findById(new MenuId(organizationCode, menuGroupCode, menuCode)).orElse(null);
	}

	@Override
	public List<Menu> selectList(MenuQueryDTO dto) {
		
		return this.repository.findAll(dto.getBooleanBuilder());							  
	}
	
	@Override
	public void save(Menu entity) {		
		this.repository.save(entity);
	}

	@Override
	public void delete(String organizationCode, String menuGroupCode, String menuCode) {
		this.repository.deleteById(new MenuId(organizationCode, menuGroupCode, menuCode));		
	}
	
}
