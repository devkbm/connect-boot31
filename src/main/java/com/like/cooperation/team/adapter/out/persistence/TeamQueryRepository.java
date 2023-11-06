package com.like.cooperation.team.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.team.application.port.dto.TeamQueryDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;

@Repository
public interface TeamQueryRepository {

	List<Team> getTeamList(TeamQueryDTO searchCondition);	
	
	List<TeamMember> getTeamMemberList(Long teamId);
}
