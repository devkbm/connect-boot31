package com.like.system.menu.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;

import com.like.system.menu.domain.MenuRoleMapping;
import com.like.system.menu.domain.MenuRoleMappingId;

public interface MenuRoleMappingJpaRepository extends JpaRepository<MenuRoleMapping, MenuRoleMappingId>, ListQuerydslPredicateExecutor<MenuRoleMapping> {

}
