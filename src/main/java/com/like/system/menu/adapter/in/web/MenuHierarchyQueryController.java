package com.like.system.menu.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.menu.application.port.dto.ResponseMenuHierarchy;
import com.like.system.menu.application.port.in.MenuHierarchySelectUseCase;


@Slf4j
@RestController
public class MenuHierarchyQueryController {

	private MenuHierarchySelectUseCase useCase;
	
	public MenuHierarchyQueryController(MenuHierarchySelectUseCase useCase) {
		this.useCase = useCase;		
	}
	
	@GetMapping("/api/system/menuhierarchy/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@RequestParam String organizationCode, @PathVariable String menuGroupCode) {				
		
		log.info("organizationCode : "+ organizationCode);
		log.info("menuGroupCode : "+ menuGroupCode);
		List<ResponseMenuHierarchy> menuGroup = useCase.select(organizationCode, menuGroupCode); 										
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}		
	

}
