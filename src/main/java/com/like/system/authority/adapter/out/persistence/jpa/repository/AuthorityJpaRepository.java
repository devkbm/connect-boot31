package com.like.system.authority.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthority;
import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthorityId;

public interface AuthorityJpaRepository extends JpaRepository<JpaAuthority, JpaAuthorityId> {

}
