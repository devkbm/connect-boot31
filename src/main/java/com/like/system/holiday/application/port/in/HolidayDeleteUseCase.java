package com.like.system.holiday.application.port.in;

import java.time.LocalDate;

public interface HolidayDeleteUseCase {
	void delete(String organizationCode, LocalDate date);
}
