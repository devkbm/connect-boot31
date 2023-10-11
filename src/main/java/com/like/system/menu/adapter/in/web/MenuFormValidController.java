package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.menu.application.port.dto.MenuSaveDTO;
import com.like.system.menu.application.port.in.MenuSelectUseCase;

@RestController
public class MenuFormValidController {

	private MenuSelectUseCase useCase;
	
	public MenuFormValidController(MenuSelectUseCase useCase) {
		this.useCase = useCase;		
	}

	@GetMapping("/api/system/menugroup/{menuGroupId}/menu/{menuId}/check")
	public ResponseEntity<?> getMenuValid(@RequestParam String organizationCode, @PathVariable String menuGroupId, @PathVariable String menuId) {						
		MenuSaveDTO menu = useCase.select(organizationCode, menuGroupId, menuId); 		
		Boolean isValid = menu == null ? true : false;			
		
		return toOne(isValid, String.format("%d 건 조회되었습니다.", menu != null ? 1 : 0));
	}
	
}
