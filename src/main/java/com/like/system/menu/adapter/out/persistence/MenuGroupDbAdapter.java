package com.like.system.menu.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuGroupJpaRepository;
import com.like.system.menu.application.port.in.dto.MenuGroupSaveDTO;
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
	public MenuGroupSaveDTO select(String organizationCode, String menuGroupCode) {
		MenuGroup entity = this.repository.findById(new MenuGroupId(organizationCode, menuGroupCode)).orElse(null);
				
		return MenuGroupSaveDTO.toDTO(entity); 
	}

	@Override
	public void save(MenuGroupSaveDTO dto) {
		this.repository.save(dto.newMenuGroup());		
	}

	@Override
	public void delete(String organizationCode, String menuGroupCode) {
		this.repository.deleteById(new MenuGroupId(organizationCode, menuGroupCode));		
	}
}
