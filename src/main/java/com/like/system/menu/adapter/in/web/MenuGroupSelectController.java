package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO.FormMenuGroup;
import com.like.system.menu.application.service.MenuCommandService;
import com.like.system.menu.domain.MenuGroup;

@RestController
public class MenuGroupSelectController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuGroupSelectController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}
			
	@GetMapping("/api/system/menugroup/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroup(@RequestParam String organizationCode, @PathVariable String menuGroupCode) {				
		
		MenuGroup menuGroup = menuCommandService.getMenuGroup(organizationCode, menuGroupCode);
		
		MenuGroupDTO.FormMenuGroup dto = FormMenuGroup.convert(menuGroup);
								
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}				
	
}
