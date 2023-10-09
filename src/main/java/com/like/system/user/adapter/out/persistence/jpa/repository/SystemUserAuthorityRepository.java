package com.like.system.user.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.user.domain.SystemUserRole;
import com.like.system.user.domain.SystemUserRoleId;

public interface SystemUserAuthorityRepository extends JpaRepository<SystemUserRole, SystemUserRoleId> {
}
