package com.like.system.holiday.application.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.holiday.application.port.in.DateInfoSelectUseCase;
import com.like.system.holiday.application.port.out.HolidaySelectPort;
import com.like.system.holiday.domain.DateInfo;
import com.like.system.holiday.domain.DateInfoCollection;
import com.like.system.holiday.domain.Holiday;

@Service
public class DateInfoSelectService implements DateInfoSelectUseCase {

	HolidaySelectPort port;
	
	public DateInfoSelectService(HolidaySelectPort port) {
		this.port = port;
	}
	
	@Override
	public DateInfoCollection select(String organizationCode, LocalDate fromDate, LocalDate toDate) {
		List<DateInfo> days = this.createDateInfoList(fromDate, toDate);
		
		List<Holiday> holidays = this.getHolidayList(organizationCode, fromDate, toDate);
		
		return new DateInfoCollection(days, holidays);
	}
	
	private List<DateInfo> createDateInfoList(LocalDate fromDate, LocalDate toDate) {
		if (fromDate.isAfter(toDate)) 
			throw new IllegalArgumentException("종료일자보다 시작일자가 큽니다.");
		
		final long dayCnt = fromDate.until(toDate, ChronoUnit.DAYS);
		List<DateInfo> list = new ArrayList<>(Math.toIntExact(dayCnt));			
		
		while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
			list.add(new DateInfo(fromDate));			
			fromDate = fromDate.plusDays(1);
		}
		
		return list;
	}
	
	private List<Holiday> getHolidayList(String organizationCode, LocalDate fromDate, LocalDate toDate) {
		return port.selectList(organizationCode, fromDate, toDate);
	}

}
