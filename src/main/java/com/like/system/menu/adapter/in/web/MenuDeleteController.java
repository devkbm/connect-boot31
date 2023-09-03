package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.service.MenuCommandService;

@RestController
public class MenuDeleteController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuDeleteController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}					
	
	@DeleteMapping("/api/system/menugroup/{menuGroupCode}menu/{menuCode}")
	public ResponseEntity<?> delMenu(@RequestParam String organizationCode, @PathVariable String menuGroupCode, @PathVariable String menuCode) {				
												
		menuCommandService.deleteMenu(organizationCode, menuGroupCode, menuCode);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
	
}
