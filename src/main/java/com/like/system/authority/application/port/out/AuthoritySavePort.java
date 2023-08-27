package com.like.system.authority.application.port.out;

import com.like.system.authority.domain.Authority;

public interface AuthoritySavePort {
	void save(Authority authority);
}
