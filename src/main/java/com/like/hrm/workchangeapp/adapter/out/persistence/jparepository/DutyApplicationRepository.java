package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.hrm.workchangeapp.domain.DutyApplication;

public interface DutyApplicationRepository extends JpaRepository<DutyApplication, Long>, QuerydslPredicateExecutor<DutyApplication> {	
	
}
