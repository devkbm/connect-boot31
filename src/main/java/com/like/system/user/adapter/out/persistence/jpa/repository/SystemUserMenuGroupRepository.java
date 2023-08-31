package com.like.system.user.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.user.domain.SystemUserMenuGroup;
import com.like.system.user.domain.SystemUserMenuGroupId;

public interface SystemUserMenuGroupRepository extends JpaRepository<SystemUserMenuGroup, SystemUserMenuGroupId> {
}
