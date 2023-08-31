package com.like.cooperation.team.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.List;

import com.like.cooperation.team.domain.QTeam;
import com.like.cooperation.team.domain.Team;
import com.like.system.user.application.service.SystemUserSearchService;
import com.like.system.user.domain.SystemUserId;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class TeamDTO {
	
	public record Search(
			Long teamId,
			String teamName
			) {
		
		private static final QTeam qType = QTeam.team;
		
		public BooleanBuilder getCondition() {									
			return new BooleanBuilder()
					.and(eqTeamId(teamId))
					.and(likeTeamName(teamName));
		}
		
		private BooleanExpression eqTeamId(Long teamId) {
			if (teamId == null) return null;
							
			return qType.teamId.eq(teamId);		
		}
		
		private BooleanExpression likeTeamName(String teamName) {
			return hasText(teamName) ? qType.teamName.like("%" + teamName + "%") : null;						
		}
		
	}
	
	@Builder
	public static record Form(
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
		
		public Team newEntity(SystemUserSearchService service) {						
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
		
		public Team modify(Team entity, SystemUserSearchService service) {
			entity.modify(teamName);
								
			List<SystemUserId> list = this.memberList.stream().map(r -> new SystemUserId(organizationCode, r)).toList();
			entity.updateMembers(service.findUsers(list));
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public static TeamDTO.Form convert(Team entity) {					
			
			if (entity == null) return null;
			
			TeamDTO.Form dto = TeamDTO.Form.builder()
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
		
}
