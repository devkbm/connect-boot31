package com.like.cooperation.team.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.application.port.in.TeamJoinUseCase;

@RestController
public class TeamJoinController {

	TeamJoinUseCase useCase;
	
	TeamJoinController(TeamJoinUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/grw/team/{teamId}/join/{userId}")
	public ResponseEntity<?> joinTeam(@PathVariable Long teamId
									 ,@PathVariable String userId) {				

		useCase.join(teamId, "001", userId);			
										 					
		return toList(null, String.format("팀에 등록 되었습니다."));
	}
}
