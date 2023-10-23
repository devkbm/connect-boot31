package com.like.system.holiday.application.port.in;

import java.time.LocalDate;

import com.like.system.holiday.application.port.dto.HolidaySaveDTO;

public interface HolidaySelectUseCase {
	HolidaySaveDTO select(String organizationCode, LocalDate date);
}
