package com.like.cooperation.team.application.port.in;

import com.like.cooperation.team.application.port.dto.TeamSaveDTO;

public interface TeamSelectUseCase {
	TeamSaveDTO select(Long teamId);
}
