package com.like.cooperation.team.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.application.port.dto.TeamSaveDTO;
import com.like.cooperation.team.application.port.in.TeamSelectUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class TeamSelectController {

	TeamSelectUseCase useCase;
	
	TeamSelectController(TeamSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/grw/team/{teamId}")
	public ResponseEntity<?> getTeam(@PathVariable Long teamId) {							
		
		TeamSaveDTO dto = useCase.select(teamId);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));					
	}
}
