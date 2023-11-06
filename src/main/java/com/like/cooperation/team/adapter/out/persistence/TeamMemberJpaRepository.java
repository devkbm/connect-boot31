package com.like.cooperation.team.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.cooperation.team.domain.TeamMember;
import com.like.cooperation.team.domain.TeamMemberId;

@Repository
public interface TeamMemberJpaRepository extends JpaRepository<TeamMember, TeamMemberId> {

}
