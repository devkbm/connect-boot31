package com.like.system.authority.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.authority.application.port.in.dto.AuthorityQueryRequestDTO;
import com.like.system.authority.application.port.out.AuthorityQueryPort;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AuthorityQueryJpaRepository implements AuthorityQueryPort {

	private JPAQueryFactory queryFactory;
	private final QJpaAuthority qAuthority = QJpaAuthority.jpaAuthority;	
	
	public AuthorityQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<JpaAuthority> getAuthorityList(AuthorityQueryRequestDTO dto) {		
		return queryFactory
				.selectFrom(qAuthority)
				.where(AuthorityMapper.toPredicate(dto))
				.fetch();
		
	}

}
