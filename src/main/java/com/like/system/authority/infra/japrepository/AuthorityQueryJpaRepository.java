package com.like.system.authority.infra.japrepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.authority.boundary.AuthorityDTO.SearchAuthority;
import com.like.system.authority.domain.Authority;
import com.like.system.authority.domain.AuthorityQueryRepository;
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
