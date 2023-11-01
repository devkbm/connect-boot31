package com.like.system.user.application.port.in;

import java.util.List;

import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;

public interface SystemUserQueryUseCase {
	List<SystemUserSaveDTO> selectList(SystemUserQueryDTO dto);
}
