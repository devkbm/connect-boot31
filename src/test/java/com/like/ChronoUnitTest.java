package com.like;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class ChronoUnitTest {

	@Test
	public void Test() {
		LocalDate startDate = LocalDate.of(1939, 9, 1);
		LocalDate endDate = LocalDate.of(1945, 9, 2);

		long months = ChronoUnit.MONTHS.between(startDate, endDate);
		long weeks = ChronoUnit.WEEKS.between(startDate, endDate);
		long days = ChronoUnit.DAYS.between(startDate, endDate);

		System.out.println("Months: " + months);
		System.out.println("Weeks: " + weeks);
		System.out.println("Days: " + days);

		LocalTime startTime = LocalTime.of(10, 35, 40);
		LocalTime endTime = LocalTime.of(10, 36, 50, 800);

		long hours = ChronoUnit.HOURS.between(startTime, endTime);
		long minutes = ChronoUnit.MINUTES.between(startTime, endTime);
		long seconds = ChronoUnit.SECONDS.between(startTime, endTime);

		System.out.println("Hours: " + hours);
		System.out.println("Minutes: " + minutes);
		System.out.println("Seconds: " + seconds);
	}
}
