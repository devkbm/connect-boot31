package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;
import com.like.system.menu.application.port.in.SystemUserMenuHierarchySelectUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SystemUserMenuHierarchyQuryController {

	SystemUserMenuHierarchySelectUseCase useCase;
	
	SystemUserMenuHierarchyQuryController(SystemUserMenuHierarchySelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/menuhierarchy/{userId}/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@RequestParam String organizationCode, @PathVariable String userId, @PathVariable String menuGroupCode) {				
		
		log.info("organizationCode : "+ organizationCode);
		log.info("menuGroupCode : "+ menuGroupCode);
		List<MenuHierarchyResponseDTO> menuGroup = useCase.select(organizationCode, userId, menuGroupCode); 										
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}
}
