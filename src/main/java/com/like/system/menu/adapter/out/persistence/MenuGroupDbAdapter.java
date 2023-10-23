package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuGroupJpaRepository;
import com.like.system.menu.application.port.dto.MenuGroupQueryDTO;
import com.like.system.menu.application.port.out.MenuGroupDeleteDbPort;
import com.like.system.menu.application.port.out.MenuGroupSaveDbPort;
import com.like.system.menu.application.port.out.MenuGroupSelectDbPort;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupId;

@Repository
public class MenuGroupDbAdapter implements MenuGroupSelectDbPort, MenuGroupSaveDbPort, MenuGroupDeleteDbPort {

	MenuGroupJpaRepository repository;
	
	MenuGroupDbAdapter(MenuGroupJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public MenuGroup select(String organizationCode, String menuGroupCode) {		
		
		return this.repository.findById(new MenuGroupId(organizationCode, menuGroupCode)).orElse(null); 
	}

	@Override
	public List<MenuGroup> selectList(MenuGroupQueryDTO dto) {		
		return this.repository.findAll(dto.getBooleanBuilder());
	}
	
	@Override
	public void save(MenuGroup entity) {
		this.repository.save(entity);		
	}

	@Override
	public void delete(String organizationCode, String menuGroupCode) {
		this.repository.deleteById(new MenuGroupId(organizationCode, menuGroupCode));		
	}
	
}
