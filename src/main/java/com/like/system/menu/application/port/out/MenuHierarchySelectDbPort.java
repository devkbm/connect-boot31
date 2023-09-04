package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.in.dto.ResponseMenuHierarchy;

public interface MenuHierarchySelectDbPort {
	List<ResponseMenuHierarchy> select(String organizationCode, String menuGroupCode);
}
