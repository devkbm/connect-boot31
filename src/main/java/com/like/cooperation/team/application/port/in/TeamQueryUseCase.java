package com.like.cooperation.team.application.port.in;

import java.util.List;

import com.like.cooperation.team.application.port.dto.TeamQueryDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;
import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;

public interface TeamQueryUseCase {
	List<Team> selectTeamList(TeamQueryDTO dto);
	
	List<TeamMember> selectTeamMemeberList(Long id);
	
	List<SystemUserSaveDTO> selectAllMemberList(SystemUserQueryDTO dto);
}
