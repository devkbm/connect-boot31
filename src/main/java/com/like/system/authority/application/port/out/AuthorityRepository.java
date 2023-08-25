package com.like.system.authority.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.authority.domain.Authority;
import com.like.system.authority.domain.AuthorityId;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {

}
