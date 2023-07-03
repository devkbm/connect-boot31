package com.like;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;

public class PeriodTest {

	@Test
	public void Test() {
		LocalDate startDate = LocalDate.of(2015, 3, 1);
		LocalDate endDate = LocalDate.of(2023, 7, 1);

		Period period = Period.between(startDate, endDate);

		System.out.println("Years: " + period.getYears());
		System.out.println("Months: " + period.getMonths());
		System.out.println("Days: " + period.getDays());
	}
	
	@Test
	public void Test2() {
		LocalDate startDate = LocalDate.of(2023, 7, 1);
		LocalDate endDate = LocalDate.of(2023, 7, 1);

		Period period = Period.between(startDate, endDate);
		
		System.out.println("2 Years: " + period.getYears());
		System.out.println("2 Months: " + period.getMonths());
		System.out.println("2 Days: " + period.getDays());
	}
}
