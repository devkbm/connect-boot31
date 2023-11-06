package com.like.cooperation.team.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.team.application.port.dto.TeamSaveDTO;
import com.like.cooperation.team.application.port.in.TeamSaveUseCase;
import com.like.cooperation.team.application.port.out.TeamCommandDbPort;
import com.like.cooperation.team.domain.Team;
import com.like.system.user.application.port.in.share.SystemUserCommonSelectUseCase;

@Transactional
@Service
public class TeamSaveService implements TeamSaveUseCase {

	TeamCommandDbPort dbPort;
	SystemUserCommonSelectUseCase userSelectUseCase;
	
	TeamSaveService(TeamCommandDbPort dbPort,
					SystemUserCommonSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
	
	@Override
	public void save(TeamSaveDTO dto) {
		Team entity = dto.teamId() == null ? null : dbPort.select(dto.teamId());
		
		if (entity == null) {
			entity = dto.newEntity(userSelectUseCase);
		} else {
			dto.modify(entity, userSelectUseCase);
		}
		
		dbPort.save(entity);
	}

}
