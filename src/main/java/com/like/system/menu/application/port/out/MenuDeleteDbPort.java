package com.like.system.menu.application.port.out;

public interface MenuDeleteDbPort {
	void delete(String organizationCode, String menuGroupCode, String menuCode);
}
