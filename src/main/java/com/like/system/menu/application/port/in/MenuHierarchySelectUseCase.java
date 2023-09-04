package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.in.dto.ResponseMenuHierarchy;

public interface MenuHierarchySelectUseCase {

	List<ResponseMenuHierarchy> select(String organizationCode, String menuGroupCode);
}
