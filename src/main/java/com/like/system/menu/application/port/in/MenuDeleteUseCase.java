package com.like.system.menu.application.port.in;

public interface MenuDeleteUseCase {
	void delete(String organizationCode, String menuGroupCode, String menuCode);
}
