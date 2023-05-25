package com.like.cooperation.workcalendar.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCalendarEventRepository extends JpaRepository<WorkCalendarEvent, Long>, QuerydslPredicateExecutor<WorkCalendarEvent> {

}
