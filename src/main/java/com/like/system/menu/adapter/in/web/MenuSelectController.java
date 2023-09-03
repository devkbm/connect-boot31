package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.in.dto.MenuDTO;
import com.like.system.menu.application.service.MenuCommandService;
import com.like.system.menu.domain.Menu;

@RestController
public class MenuSelectController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuSelectController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}				
	
	@GetMapping("/api/system/menugroup/{menuGroupCode}/menu/{menuCode}")
	public ResponseEntity<?> getMenu(@RequestParam String organizationCode, @PathVariable String menuGroupCode, @PathVariable String menuCode) {				
		
		Menu menu = menuCommandService.getMenu(organizationCode, menuGroupCode, menuCode); 		
		
		MenuDTO.FormMenu dto = MenuDTO.FormMenu.convert(menu);			
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
}
