package com.like.system.holiday.application.port.in;

import java.time.LocalDate;

import com.like.system.holiday.domain.DateInfoCollection;

public interface DateInfoSelectUseCase {

	DateInfoCollection select(String organizationCode, LocalDate fromDate, LocalDate toDate);
}
