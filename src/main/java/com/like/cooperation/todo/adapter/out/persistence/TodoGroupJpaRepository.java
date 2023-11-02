package com.like.cooperation.todo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.cooperation.todo.domain.TodoGroup;

@Repository
public interface TodoGroupJpaRepository extends JpaRepository<TodoGroup,Long> , ListQuerydslPredicateExecutor<TodoGroup> {

}
