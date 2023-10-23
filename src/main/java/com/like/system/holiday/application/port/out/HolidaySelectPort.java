package com.like.system.holiday.application.port.out;

import java.time.LocalDate;
import java.util.List;

import com.like.system.holiday.domain.Holiday;

public interface HolidaySelectPort {
	Holiday select(String organizationCode, LocalDate date);
	
	List<Holiday> selectList(String organizationCode, LocalDate fromDate, LocalDate toDate);
}
