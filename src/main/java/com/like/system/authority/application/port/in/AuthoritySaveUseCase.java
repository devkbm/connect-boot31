package com.like.system.authority.application.port.in;

import com.like.system.authority.application.port.in.dto.AuthoritySaveDTO;

public interface AuthoritySaveUseCase {
	void save(AuthoritySaveDTO dto);
}
