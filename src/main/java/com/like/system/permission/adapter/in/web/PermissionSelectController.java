package com.like.system.permission.adapter.in.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionSelectController {

	
	@GetMapping("/api/system/permission/islogin")
	public boolean get() {		
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}
}
