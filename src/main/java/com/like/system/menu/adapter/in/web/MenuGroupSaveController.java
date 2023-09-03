package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO;
import com.like.system.menu.application.service.MenuCommandService;

@RestController
public class MenuGroupSaveController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuGroupSaveController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}
		
	@PostMapping("/api/system/menugroup/{id}")
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.FormMenuGroup dto) {							
																			
		menuCommandService.saveMenuGroup(dto);			
										 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
