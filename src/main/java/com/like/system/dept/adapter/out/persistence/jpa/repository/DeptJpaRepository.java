package com.like.system.dept.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;

import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptId;

public interface DeptJpaRepository extends JpaRepository<Dept, DeptId>, ListQuerydslPredicateExecutor<Dept> {
				
}