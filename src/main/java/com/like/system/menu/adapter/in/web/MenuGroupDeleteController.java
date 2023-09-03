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
public class MenuGroupDeleteController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuGroupDeleteController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}			
		
	@DeleteMapping("/api/system/menugroup/{menuGroupCode}")
	public ResponseEntity<?> delCodeGroup(@RequestParam String organizationCode, @PathVariable String menuGroupCode) {				
												
		menuCommandService.deleteMenuGroup(organizationCode, menuGroupCode);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
	
}
