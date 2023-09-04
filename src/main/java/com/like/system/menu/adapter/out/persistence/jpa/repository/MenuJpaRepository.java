package com.like.system.menu.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;

import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuId;

public interface MenuJpaRepository extends JpaRepository<Menu, MenuId>, ListQuerydslPredicateExecutor<Menu> {

}
