package com.like.cooperation.team.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.cooperation.team.domain.Team;

public interface TeamJpaRepository extends JpaRepository<Team, Long> , QuerydslPredicateExecutor<Team> {

}
