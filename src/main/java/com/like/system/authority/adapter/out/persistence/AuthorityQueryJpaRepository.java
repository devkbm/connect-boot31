package com.like.system.authority.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.authority.adapter.in.web.AuthorityDTO.SearchAuthority;
import com.like.system.authority.application.port.out.AuthorityQueryRepository;
import com.like.system.authority.domain.Authority;
import com.like.system.authority.domain.QAuthority;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AuthorityQueryJpaRepository implements AuthorityQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QAuthority qAuthority = QAuthority.authority;	
	
	public AuthorityQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<Authority> getAuthorityList(SearchAuthority condition) {
		return queryFactory
				.selectFrom(qAuthority)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

}
