package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.in.MenuSelectUseCase;
import com.like.system.menu.application.port.in.dto.MenuSaveDTO;

@RestController
public class MenuSelectController {
	
	private MenuSelectUseCase useCase;		
			
	public MenuSelectController(MenuSelectUseCase useCase) {
		this.useCase = useCase;		
	}				
	
	@GetMapping("/api/system/menugroup/{menuGroupCode}/menu/{menuCode}")
	public ResponseEntity<?> getMenu(@RequestParam String organizationCode
									,@PathVariable String menuGroupCode
									,@PathVariable String menuCode) {				
		
		MenuSaveDTO dto = useCase.select(organizationCode, menuGroupCode, menuCode);					
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
}
