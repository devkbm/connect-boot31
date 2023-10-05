package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.dutycode.domain.DutyCode;
import com.like.hrm.workchangeapp.application.port.dto.DutyApplicationDTO;
import com.like.hrm.workchangeapp.domain.DutyApplication;
import com.like.hrm.workchangeapp.domain.QDutyApplication;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DutyApplicationQueryJpaRepository implements DutyApplicationQueryRepository {

	private JPAQueryFactory	queryFactory;
	
	public DutyApplicationQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<DutyApplication> getDutyApplicationList(DutyApplicationDTO.Search condition) {
		return queryFactory
				.selectFrom(QDutyApplication.dutyApplication)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public long getDutyApplicationCount(String staffId, List<DutyCode> dutyCodeList, LocalDate fromDate,
			LocalDate toDate) {
		
		QDutyApplication qDutyApplication = QDutyApplication.dutyApplication;							
		List<String> dutyCodes = dutyCodeList.stream().map(e -> e.getDutyCode()).toList();
				
		return queryFactory
				.selectFrom(QDutyApplication.dutyApplication)
				.where(qDutyApplication.staffId.eq(staffId)
				  .and(qDutyApplication.dutyCode.in(dutyCodes)))
				.fetch()
				.size();				
	}

}
