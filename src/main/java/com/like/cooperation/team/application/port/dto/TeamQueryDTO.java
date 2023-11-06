package com.like.cooperation.team.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.cooperation.team.domain.QTeam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public record TeamQueryDTO(
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