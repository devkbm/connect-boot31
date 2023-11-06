package com.like.cooperation.team.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.team.application.port.dto.TeamQueryDTO;
import com.like.cooperation.team.application.port.in.TeamQueryUseCase;
import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;
import com.like.system.core.message.MessageUtil;
import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;

@RestController
public class TeamQueryController {

	private TeamQueryUseCase service;
	
	public TeamQueryController(TeamQueryUseCase service) {
		this.service = service;
	}
	
	@GetMapping("/api/grw/team")
	public ResponseEntity<?> getTeamList(@ModelAttribute TeamQueryDTO searchCondition) {
						
		List<Team> list = service.selectTeamList(searchCondition);				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
	
	@GetMapping("/api/grw/team/{id}/member")
	public ResponseEntity<?> getTeamMemberList(@PathVariable Long id) {
						
		List<TeamMember> list = service.selectTeamMemeberList(id);				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));												
	}
	
	@GetMapping("/api/grw/team/allmember")
	public ResponseEntity<?> getAllMemberList(SystemUserQueryDTO condition) {
				
		List<SystemUserSaveDTO> list = service.selectAllMemberList(condition);						 				
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
}
