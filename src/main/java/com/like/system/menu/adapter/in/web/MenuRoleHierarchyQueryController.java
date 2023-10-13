package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.dto.MenuRoleMappingHierarchyResponseDTO;
import com.like.system.menu.application.port.in.MenuRoleHierarchySelectUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MenuRoleHierarchyQueryController {

	MenuRoleHierarchySelectUseCase useCase;
	
	MenuRoleHierarchyQueryController(MenuRoleHierarchySelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/menurolehierarchy/{menuGroupCode}/{roleCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@RequestParam String organizationCode, @PathVariable String menuGroupCode, @PathVariable String roleCode) {				
		
		log.info("organizationCode : "+ organizationCode);
		log.info("menuGroupCode : "+ menuGroupCode);
		List<MenuRoleMappingHierarchyResponseDTO> menuGroup = useCase.select(organizationCode, menuGroupCode, roleCode); 										
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}	
}
