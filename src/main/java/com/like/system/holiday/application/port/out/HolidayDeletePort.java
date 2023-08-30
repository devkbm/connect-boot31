package com.like.system.holiday.application.port.out;

import java.time.LocalDate;

public interface HolidayDeletePort {
	void delete(String organizationCode, LocalDate date);
}
