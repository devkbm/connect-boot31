package com.like.cooperation.team.application.port.dto;

import com.like.cooperation.team.domain.TeamMember;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record TeamMemberSaveDTO(
		@NotEmpty(message = "팅 ID는 필수 입력 값입니다.")
		Long teamId,
		@NotEmpty(message = "유저 ID는 필수 입력 값입니다.")
		String userId,
		String authority
		) {
	
	public static TeamMemberSaveDTO toDTO(TeamMember entity) {
		if (entity == null) return null;
					
		return TeamMemberSaveDTO.builder()
				   .teamId(entity.getTeam().getTeamId())
				   .userId(entity.getId().getUserId())
				   .authority(entity.getAuthority())
				   .build();
	}
}