package com.like.cooperation.team.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.application.port.dto.TeamDTO;
import com.like.cooperation.team.application.service.TeamService;
import com.like.cooperation.team.domain.Team;
import com.like.system.core.message.MessageUtil;

@RestController
public class TeamController {
		
	private TeamService teamService;				
	
	public TeamController(TeamService teamService) {
		this.teamService = teamService;		
	}
				
	@GetMapping("/api/grw/team/{teamId}")
	public ResponseEntity<?> getTeam(@PathVariable Long teamId) {
						
		Team team = teamService.getTeam(teamId);				
		
		TeamDTO.Form dto = TeamDTO.Form.convert(team);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));					
	}
		
	@PostMapping("/api/grw/team")
	public ResponseEntity<?> saveTeam(@RequestBody @Valid TeamDTO.Form dto) {				
		 												
		teamService.saveTeam(dto);		
										 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	/*
	@GetMapping("/api/grw/team/{id}/member")
	public ResponseEntity<?> getTeamMemberList(@PathVariable("id") Long teamId) {
						
		List<SystemUser> memberList = teamService.getTeamMemberList(teamId);												
		
		return WebControllerUtil.getResponse(memberList
											,"{0}건 조회 되었습니다.".formatted(memberList.size()));
	}
	*/		
				
	
	@DeleteMapping("/api/grw/team/{teamId}")
	public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {					
		
		teamService.deleteTeam(teamId);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
	@PostMapping("/api/grw/team/{teamId}/join/{userId}")
	public ResponseEntity<?> joinTeam(@PathVariable Long teamId
									 ,@PathVariable String userId) {				

		teamService.joinTeam(teamId, userId);			
										 					
		return toList(null, String.format("팀에 등록 되었습니다."));
	}
			
}
