package com.like.cooperation.workcalendar.adapter.out.persistence.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.cooperation.workcalendar.domain.WorkCalendar;

@Repository
public interface WorkCalendarJpaRepository extends JpaRepository<WorkCalendar, Long>, QuerydslPredicateExecutor<WorkCalendar> { 

}
