package com.like.cooperation.workcalendar.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCalendarMemberRepository extends JpaRepository<WorkCalendarMember, WorkCalendarMemberId>, QuerydslPredicateExecutor<WorkCalendarMember> { 

}
