package com.like.cooperation.team.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.team.application.port.in.TeamDeleteUseCase;
import com.like.cooperation.team.application.port.out.TeamCommandDbPort;

@Transactional
@Service
public class TeamDeleteService implements TeamDeleteUseCase {

	TeamCommandDbPort dbPort;
	
	TeamDeleteService(TeamCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(Long teamId) {
		this.dbPort.delete(teamId);
	}

}
