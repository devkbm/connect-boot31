package com.like;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DurationTest {

	@Test
	public void Test() {
		LocalTime start = LocalTime.of(10, 35, 40);
		LocalTime end = LocalTime.of(10, 36, 50, 800);

		Duration duration = Duration.between(start, end);

		System.out.println("Seconds: " + duration.getSeconds());
		System.out.println("Nano Seconds: " + duration.getNano());
	}
	
	@Test
	public void Test2() {
		LocalDateTime start = LocalDateTime.of(LocalDate.of(2023, 7, 3), LocalTime.of(10, 35, 40));
		LocalDateTime end = LocalDateTime.of(LocalDate.of(2023, 7, 3), LocalTime.of(12, 36, 41));

		Duration duration = Duration.between(start, end);
		
		System.out.println("2 Seconds: " + duration.getSeconds());
		System.out.println("2 Nano Seconds: " + duration.getNano());
		
		// 시간 변환
		LocalTime time = LocalTime.of(0,0).plusSeconds(duration.getSeconds());
		
		long hour = time.getHour();
		long min =  time.getMinute();
		long sec =  time.getSecond();
		int nano = duration.getNano();
		
		System.out.println("2 hour: " + hour);
		System.out.println("2 min: " + min);
		System.out.println("2 sec: " + sec);
		System.out.println("2 nano: " + nano);
	}
	
	@Test
	public void Test3() {
		LocalDateTime start = LocalDateTime.of(LocalDate.of(2023, 7, 3), LocalTime.of(10, 35, 40));
		LocalDateTime end = LocalDateTime.of(LocalDate.of(2023, 7, 3), LocalTime.of(12, 37, 41));

		Duration duration = Duration.between(start, end);
		
		BigDecimal seconds = new BigDecimal(duration.getSeconds());
		BigDecimal hour = seconds.divide(new BigDecimal("3600"), 2, RoundingMode.DOWN);
		//double hour = (double)duration.getSeconds() / 3600;
		
		System.out.println("3 Seconds: " + duration.getSeconds());
		System.out.println("3 Nano Seconds: " + duration.getNano());
		System.out.println("3 Hours : " + hour);
		
	}
}
