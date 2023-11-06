package com.like.cooperation.team.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.team.application.port.out.TeamCommandDbPort;
import com.like.cooperation.team.domain.Team;

@Repository
public class TeamCommandDbAdapter implements TeamCommandDbPort {

	TeamJpaRepository repository;
	
	TeamCommandDbAdapter(TeamJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Team select(Long teamId) {
		return this.repository.findById(teamId).orElse(null);
	}

	@Override
	public void save(Team entity) {
		this.repository.save(entity);
	}

	@Override
	public void delete(Long teamId) {
		this.repository.deleteById(teamId);
	}

}
