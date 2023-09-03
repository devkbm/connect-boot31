package com.like.system.menu.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.application.port.in.dto.MenuDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupId;
import com.like.system.menu.domain.MenuGroupRepository;
import com.like.system.menu.domain.MenuId;
import com.like.system.menu.domain.MenuRepository;

@Service
@Transactional
public class MenuCommandService {

	private MenuGroupRepository menuGroupRepository;
	private MenuRepository menuRepository;	
			
	public MenuCommandService(MenuGroupRepository menuGroupRepository
							 ,MenuRepository menuRepository) {
		this.menuGroupRepository = menuGroupRepository;
		this.menuRepository = menuRepository;		
	}

	public MenuGroup getMenuGroup(String organizationCode, String menuGroupCode) {
		return menuGroupRepository.findById(new MenuGroupId(organizationCode, menuGroupCode)).orElse(null);
	}
	
	public void saveMenuGroup(MenuGroup codeGroup) {
		menuGroupRepository.save(codeGroup);	
	}
	
	public void saveMenuGroup(MenuGroupDTO.FormMenuGroup dto) {
		MenuGroup menuGroup = dto.menuGroupCode() != null ? menuGroupRepository.findById(new MenuGroupId(dto.organizationCode(), dto.menuGroupCode())).orElse(null) : null; 			
		
		if (menuGroup == null) {
			menuGroup = dto.newMenuGroup();
		} else {
			dto.modifyMenuGroup(menuGroup);
		}
		
		menuGroupRepository.save(menuGroup);	
	}
	
	public void deleteMenuGroup(String organizationCode, String menuGroupCode) {
		menuGroupRepository.deleteById(new MenuGroupId(organizationCode, menuGroupCode));
	}
	
	public Menu getMenu(String organizationCode, String menuGroupCode, String menuCode) {
		return menuRepository.findById(new MenuId(organizationCode, menuGroupCode, menuCode)).orElse(null);
	}
	
	public void saveMenu(Menu menu) throws Exception {			
		menuRepository.save(menu);		
	}
	
	public void saveMenu(MenuDTO.FormMenu dto) {
		MenuGroup menuGroup = menuGroupRepository.findById(new MenuGroupId(dto.organizationCode(), dto.menuGroupCode())).orElse(null);
		Menu menu = menuRepository.findById(new MenuId(dto.organizationCode(), dto.menuGroupCode(), dto.menuCode())).orElse(null);
		Menu parent = dto.parentMenuCode() != null ? menuRepository.findById(new MenuId(dto.organizationCode(), dto.menuGroupCode(), dto.parentMenuCode())).orElse(null) : null; 						
					
		if (menu == null) {
			menu = dto.newMenu(menuGroup);
		} else {
			dto.modifyMenu(menu, parent, menuGroup);
		}		
		
		menuRepository.save(menu);	
	}
	
	public void deleteMenu(String organizationCode, String menuGroupCode, String menuCode) {		
		menuRepository.deleteById(new MenuId(organizationCode, menuGroupCode, menuCode));
	}		
	
}
