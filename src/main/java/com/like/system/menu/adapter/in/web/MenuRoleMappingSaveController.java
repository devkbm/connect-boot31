package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.dto.MenuRoleMappingSaveDTO;
import com.like.system.menu.application.port.in.MenuRoleMappingSaveUseCase;

import jakarta.validation.Valid;

@RestController
public class MenuRoleMappingSaveController {

	MenuRoleMappingSaveUseCase useCase;
	
	MenuRoleMappingSaveController(MenuRoleMappingSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/system/menurole")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid List<MenuRoleMappingSaveDTO> dto) throws Exception {												
									
		useCase.save(dto);																			
														 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}		
}
