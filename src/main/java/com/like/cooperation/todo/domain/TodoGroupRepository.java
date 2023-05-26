package com.like.cooperation.todo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoGroupRepository extends JpaRepository<TodoGroup,Long> , ListQuerydslPredicateExecutor<TodoGroup> {

}
