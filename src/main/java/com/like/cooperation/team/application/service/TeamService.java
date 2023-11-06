package com.like.cooperation.team.application.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.cooperation.team.adapter.out.persistence.TeamJpaRepository;
import com.like.cooperation.team.application.port.dto.TeamSaveDTO;
import com.like.cooperation.team.domain.Team;
import com.like.system.user.application.port.in.share.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;

@Service
@Transactional
public class TeamService {
	
	private TeamJpaRepository teamRepository;	
	private SystemUserCommonSelectUseCase userService;	
	
	public TeamService(TeamJpaRepository teamRepository					  
					  ,SystemUserCommonSelectUseCase userService) {
		this.teamRepository = teamRepository;		
		this.userService = userService;		
	}
	
	public Team getTeam(Long teamId) {
		return teamRepository.findById(teamId).orElse(null);
	}
		
	public void saveTeam(TeamSaveDTO dto) {		
		Team entity = dto.teamId() == null ? null : teamRepository.findById(dto.teamId()).orElse(null);
		
		if (entity == null) {
			entity = dto.newEntity(userService);
		} else {
			dto.modify(entity, userService);
		}
		
		teamRepository.save(entity);
	}
	
	public void deleteTeam(Long teamId) {
		teamRepository.deleteById(teamId);
	}
	
	/**
	 * 팀에 가입한다.
	 * @param teamId 팀 엔티티 Id
	 * @param userId 유저 엔티티 Id
	 * @return 
	 */
	public void joinTeam(Long teamId, String userId) {
		Team team = teamRepository.findById(teamId).orElse(null);
		SystemUser member = userService.findUser("001", userId);			
		
		team.addMember(member);			
	}	
}
