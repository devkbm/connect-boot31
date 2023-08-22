package com.like.system.menu.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.boundary.MenuGroupDTO.FormMenuGroup;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.service.MenuCommandService;

@RestController
public class MenuController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}
			
	@GetMapping("/api/system/menugroup/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroup(@RequestParam String organizationCode, @PathVariable String menuGroupCode) {				
		
		MenuGroup menuGroup = menuCommandService.getMenuGroup(organizationCode, menuGroupCode);
		
		MenuGroupDTO.FormMenuGroup dto = FormMenuGroup.convert(menuGroup);
								
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}		
		
	@PostMapping("/api/system/menugroup/{id}")
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.FormMenuGroup dto) {							
																			
		menuCommandService.saveMenuGroup(dto);			
										 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
		
	@DeleteMapping("/api/system/menugroup/{menuGroupCode}")
	public ResponseEntity<?> delCodeGroup(@RequestParam String organizationCode, @PathVariable String menuGroupCode) {				
												
		menuCommandService.deleteMenuGroup(organizationCode, menuGroupCode);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
	
	@GetMapping("/api/system/menugroup/{menuGroupCode}/menu/{menuCode}")
	public ResponseEntity<?> getMenu(@RequestParam String organizationCode, @PathVariable String menuGroupCode, @PathVariable String menuCode) {				
		
		Menu menu = menuCommandService.getMenu(organizationCode, menuGroupCode, menuCode); 		
		
		MenuDTO.FormMenu dto = MenuDTO.FormMenu.convert(menu);			
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
	
		
	@PostMapping("/api/system/menugroup/{menuGroupCode}/menu/{menuCode}")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO.FormMenu dto) throws Exception {												
									
		menuCommandService.saveMenu(dto);																			
														 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/system/menugroup/{menuGroupCode}menu/{menuCode}")
	public ResponseEntity<?> delMenu(@RequestParam String organizationCode, @PathVariable String menuGroupCode, @PathVariable String menuCode) {				
												
		menuCommandService.deleteMenu(organizationCode, menuGroupCode, menuCode);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
	
}
