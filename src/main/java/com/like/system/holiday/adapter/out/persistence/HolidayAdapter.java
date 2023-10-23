package com.like.system.holiday.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.holiday.adapter.out.persistence.jpa.repository.HolidayJpaRepository;
import com.like.system.holiday.application.port.out.HolidayDeletePort;
import com.like.system.holiday.application.port.out.HolidaySavePort;
import com.like.system.holiday.application.port.out.HolidaySelectPort;
import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayId;
import com.like.system.holiday.domain.QHoliday;
import com.querydsl.core.types.Predicate;

@Repository
public class HolidayAdapter implements HolidaySelectPort, HolidaySavePort, HolidayDeletePort {

	HolidayJpaRepository repository;
	
	public HolidayAdapter(HolidayJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Holiday select(String organizationCode, LocalDate date) {		
		return this.repository.findById(new HolidayId(organizationCode, date)).orElse(null);
	}

	@Override
	public void save(Holiday entity) {		
		
		this.repository.save(entity);
	}

	@Override
	public void delete(String organizationCode, LocalDate date) {
		this.repository.deleteById(new HolidayId(organizationCode, date));		
	}

	@Override
	public List<Holiday> selectList(String organizationCode, LocalDate fromDate, LocalDate toDate) {
		QHoliday qHoliday = QHoliday.holiday;
		Predicate predicate = qHoliday.id.organizationCode.eq(organizationCode)
						 .and(qHoliday.id.date.goe(fromDate).and(qHoliday.id.date.loe(toDate)));
				
		return this.repository.findAll(predicate);
	}

}
