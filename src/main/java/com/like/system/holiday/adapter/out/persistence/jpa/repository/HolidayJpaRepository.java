package com.like.system.holiday.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;

import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayId;

public interface HolidayJpaRepository extends JpaRepository<Holiday, HolidayId>, ListQuerydslPredicateExecutor<Holiday> {
	
}
