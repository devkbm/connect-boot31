package com.like.cooperation.team.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.team.application.port.in.TeamJoinUseCase;
import com.like.cooperation.team.application.port.out.TeamCommandDbPort;
import com.like.cooperation.team.domain.Team;
import com.like.system.user.application.port.in.share.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;

@Transactional
@Service
public class TeamJoinService implements TeamJoinUseCase {

	TeamCommandDbPort dbPort;
	SystemUserCommonSelectUseCase userSelectUseCase;
	
	TeamJoinService(TeamCommandDbPort dbPort,
					SystemUserCommonSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
	
	@Override
	public void join(Long teamId, String organizationCode, String userId) {
		Team team = dbPort.select(teamId);
		SystemUser member = userSelectUseCase.findUser(organizationCode, userId);			
		
		team.addMember(member);	
		
		dbPort.save(team);
	}

}
