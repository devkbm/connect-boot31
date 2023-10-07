package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.hrm.workchangeapp.domain.WorkChangeCode;

public interface WorkChangeCodeRepository extends JpaRepository<WorkChangeCode, String>, QuerydslPredicateExecutor<WorkChangeCode> {

}
