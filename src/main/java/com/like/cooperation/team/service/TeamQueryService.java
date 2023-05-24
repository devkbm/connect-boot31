package com.like.cooperation.team.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.team.boundary.TeamDTO;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;
import com.like.cooperation.team.domain.TeamQueryRepository;
import com.like.system.user.boundary.SystemUserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.SystemUserQueryService;

@Service
@Transactional(readOnly=true)
public class TeamQueryService {

	private TeamQueryRepository teamQueryRepository;
	private SystemUserQueryService userQueryService;
	
	public TeamQueryService(TeamQueryRepository teamQueryRepository
						   ,SystemUserQueryService userQueryService) {
		this.teamQueryRepository = teamQueryRepository;
		this.userQueryService = userQueryService;
	}
	
	/**
	 * 조건에 해당하는 팀 명단을 조회한다.
	 * @param searchCondition 조회조건
	 * @return List<Team> 팀 명단
	 */
	public List<Team> getTeamList(TeamDTO.Search searchCondition) {	
		return teamQueryRepository.getTeamList(searchCondition);
	}
	
	public List<TeamMember> getTeamMemberList(Long id) {
		return teamQueryRepository.getTeamMemberList(id);
	}	
	
	/**
	 * 조건에 해당하는 유저 정보를 조회한다.
	 * @param searchCondition 조회 조건
	 * @return User 
	 */
	public List<SystemUser> getAllMember(SystemUserDTO.Search searchCondition) {
		return userQueryService.getUserList(searchCondition);
	}	
	
}
