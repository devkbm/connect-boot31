package com.like.cooperation.team.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.application.port.in.TeamDeleteUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class TeamDeleteController {

	TeamDeleteUseCase useCase;
	
	TeamDeleteController(TeamDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/grw/team/{teamId}")
	public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {					
		
		useCase.delete(teamId);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
