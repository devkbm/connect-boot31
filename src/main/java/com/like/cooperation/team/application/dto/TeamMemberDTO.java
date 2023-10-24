package com.like.cooperation.team.application.dto;

import jakarta.validation.constraints.NotEmpty;

import com.like.cooperation.team.domain.TeamMember;

import lombok.Builder;

public class TeamMemberDTO {

	@Builder
	public static record Form(
			@NotEmpty(message = "팅 ID는 필수 입력 값입니다.")
			Long teamId,
			@NotEmpty(message = "유저 ID는 필수 입력 값입니다.")
			String userId,
			String authority
			) {
		
		public static Form convert(TeamMember entity) {
			if (entity == null) return null;
						
			return Form.builder()
					   .teamId(entity.getTeam().getTeamId())
					   .userId(entity.getId().getUserId())
					   .authority(entity.getAuthority())
					   .build();
		}
	}
		
}
