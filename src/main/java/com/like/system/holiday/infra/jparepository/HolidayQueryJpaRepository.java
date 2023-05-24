package com.like.system.holiday.infra.jparepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayQueryRepository;
import com.like.system.holiday.domain.QHoliday;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HolidayQueryJpaRepository implements HolidayQueryRepository {

	private JPAQueryFactory  queryFactory;
	private static final QHoliday qHoliday = QHoliday.holiday;
	
	public HolidayQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Holiday> getHoliday(String organizationCode, LocalDate fromDate, LocalDate toDate) {		
		
		return queryFactory
				.selectFrom(qHoliday)
				.where(qHoliday.id.organizationCode.eq(organizationCode)
			 	  .and(qHoliday.id.date.goe(fromDate).and(qHoliday.id.date.loe(toDate))))
				.fetch();				
	}

}
