package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuRoleMappingJpaRepository;
import com.like.system.menu.application.port.dto.MenuRoleMappingSaveDTO;
import com.like.system.menu.application.port.out.MenuRoleMappingSaveDbPort;

@Repository
public class MenuRoleMappingDbAdapter implements MenuRoleMappingSaveDbPort {

	MenuRoleMappingJpaRepository repository;
	
	MenuRoleMappingDbAdapter(MenuRoleMappingJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void save(List<MenuRoleMappingSaveDTO> entityList) {
		this.repository.saveAll(entityList.stream().map(e -> e.toEntity()).toList());		
	}

}
