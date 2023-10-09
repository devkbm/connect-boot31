package com.like.system.role.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRole;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRoleId;

public interface RoleJpaRepository extends JpaRepository<JpaRole, JpaRoleId> {

}
