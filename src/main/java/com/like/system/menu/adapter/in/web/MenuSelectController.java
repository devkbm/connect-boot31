package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.dto.MenuQueryConditionDTO;
import com.like.system.menu.application.port.dto.MenuSaveDTO;
import com.like.system.menu.application.port.in.MenuSelectUseCase;
import com.like.system.menu.domain.MenuType;

import jakarta.validation.Valid;

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
	
	@GetMapping("/api/system/menu")
	public ResponseEntity<?> getMenuList(@Valid MenuQueryConditionDTO dto) {				
		
		List<MenuSaveDTO> dtoList = useCase.selectList(dto);																	
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
	
	@GetMapping("/api/system/menu/menutype")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<HtmlSelectOptionRecord> list = new ArrayList<HtmlSelectOptionRecord>();
		
		for (MenuType menuType : MenuType.values()) {			
			list.add(new HtmlSelectOptionRecord(menuType.getLabel(), menuType.toString()));
		}
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
