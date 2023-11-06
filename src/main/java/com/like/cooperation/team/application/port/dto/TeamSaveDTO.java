package com.like.cooperation.team.application.port.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.like.cooperation.team.domain.Team;
import com.like.system.user.application.port.in.share.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUserId;

import lombok.Builder;

@Builder
public record TeamSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,
		String organizationCode,
		Long teamId,
		String teamName,
		List<String> memberList
		) {
	
	public Team newEntity(SystemUserCommonSelectUseCase service) {						
		Team entity = null;
					
		if (memberList == null || memberList.isEmpty()) {
			entity = new Team(teamName);
		} else {
			List<SystemUserId> list = this.memberList.stream().map(r -> new SystemUserId(organizationCode, r)).toList();
			entity = new Team(teamName, service.findUsers(list));
		}										
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public Team modify(Team entity, SystemUserCommonSelectUseCase service) {
		entity.modify(teamName);
							
		List<SystemUserId> list = this.memberList.stream().map(r -> new SystemUserId(organizationCode, r)).toList();
		entity.updateMembers(service.findUsers(list));
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public static TeamSaveDTO toDTO(Team entity) {					
		
		if (entity == null) return null;
		
		TeamSaveDTO dto = TeamSaveDTO.builder()
									   .createdDt(entity.getCreatedDt())
									   .createdBy(entity.getCreatedBy().getLoggedUser())
									   .modifiedDt(entity.getModifiedDt())
									   .modifiedBy(entity.getModifiedBy().getLoggedUser())
									   .teamId(entity.getTeamId())
									   .teamName(entity.getTeamName())
									   .memberList(entity.getMembers().stream()
																	  .map(r -> r.getId().getUserId())
																	  .toList())																	  
									   .build();		
		return dto;
	}
}