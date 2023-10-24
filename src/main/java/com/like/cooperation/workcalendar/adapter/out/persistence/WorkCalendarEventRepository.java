package com.like.cooperation.workcalendar.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

@Repository
public interface WorkCalendarEventRepository extends JpaRepository<WorkCalendarEvent, Long>, QuerydslPredicateExecutor<WorkCalendarEvent> {

}
