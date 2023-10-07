package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.workchangeapp.application.port.dto.DutyApplicationDTO;
import com.like.hrm.workchangeapp.domain.WorkChangeApplication;
import com.like.hrm.workchangeapp.domain.WorkChangeCode;
import com.like.hrm.workchangeapp.domain.QWorkChangeApplication;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DutyApplicationQueryJpaRepository implements DutyApplicationQueryRepository {

	private JPAQueryFactory	queryFactory;
	
	public DutyApplicationQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<WorkChangeApplication> getDutyApplicationList(DutyApplicationDTO.Search condition) {
		return queryFactory
				.selectFrom(QWorkChangeApplication.workChangeApplication)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public long getDutyApplicationCount(String staffId, List<WorkChangeCode> dutyCodeList, LocalDate fromDate,
			LocalDate toDate) {
		
		QWorkChangeApplication qDutyApplication = QWorkChangeApplication.workChangeApplication;							
		List<String> dutyCodes = dutyCodeList.stream().map(e -> e.getDutyCode()).toList();
				
		return queryFactory
				.selectFrom(QWorkChangeApplication.workChangeApplication)
				.where(qDutyApplication.staffId.eq(staffId)
				  .and(qDutyApplication.dutyCode.in(dutyCodes)))
				.fetch()
				.size();				
	}

}
