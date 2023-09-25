package com.like.hrm.staff.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;

@Repository
public interface StaffJpaRepository extends JpaRepository<Staff, StaffId>, QuerydslPredicateExecutor<Staff> {			
}
