package com.like.system.user.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.user.domain.SystemUserAuthority;
import com.like.system.user.domain.SystemUserAuthorityId;

public interface SystemUserAuthorityRepository extends JpaRepository<SystemUserAuthority, SystemUserAuthorityId> {
}
