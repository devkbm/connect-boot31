package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.in.dto.MenuDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO;
import com.like.system.menu.application.port.in.dto.ResponseMenuHierarchy;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO.FormMenuGroup;
import com.like.system.menu.application.service.MenuQueryService;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuType;

@Slf4j
@RestController
public class MenuQueryController {

	private MenuQueryService menuQueryService;
	
	public MenuQueryController(MenuQueryService menuQueryService) {
		this.menuQueryService = menuQueryService;		
	}
	
	@GetMapping("/api/system/menutest/{menuGroupId}")
	public ResponseEntity<?> getMenuGroupHierachyTest(@RequestParam String organizationCode, @PathVariable String menuGroupId) {				
		
		List<ResponseMenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(organizationCode, menuGroupId); 							
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}
	
	@GetMapping("/api/system/menuhierarchy/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@RequestParam String organizationCode, @PathVariable String menuGroupCode) {				
		
		log.info("organizationCode : "+ organizationCode);
		log.info("menuGroupCode : "+ menuGroupCode);
		List<ResponseMenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(organizationCode, menuGroupCode); 										
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}	
	
	@GetMapping("/api/system/menu")
	public ResponseEntity<?> getMenuList(@Valid MenuDTO.Search dto) {				
		
		List<Menu> list = menuQueryService.getMenuList(dto);			
		
		List<MenuDTO.FormMenu> dtoList = list.stream()
											 .map(e -> MenuDTO.FormMenu.convert(e))
											 .toList();											 
		
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
