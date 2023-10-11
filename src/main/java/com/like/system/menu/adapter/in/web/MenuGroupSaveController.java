package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.menu.application.port.in.MenuGroupSaveUseCase;

@RestController
public class MenuGroupSaveController {
	
	private MenuGroupSaveUseCase useCase;		
			
	public MenuGroupSaveController(MenuGroupSaveUseCase useCase) {
		this.useCase = useCase;		
	}
		
	@PostMapping("/api/system/menugroup/{id}")
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupSaveDTO dto) {							
																			
		useCase.save(dto);			
										 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
