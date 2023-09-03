package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.in.dto.MenuDTO;
import com.like.system.menu.application.service.MenuCommandService;

@RestController
public class MenuSaveController {
	
	private MenuCommandService menuCommandService;		
			
	public MenuSaveController(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;		
	}						
		
	@PostMapping("/api/system/menugroup/{menuGroupCode}/menu/{menuCode}")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO.FormMenu dto) throws Exception {												
									
		menuCommandService.saveMenu(dto);																			
														 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}		
}
