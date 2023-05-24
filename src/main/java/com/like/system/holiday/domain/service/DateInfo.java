package com.like.system.holiday.domain.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import com.like.system.holiday.domain.Holiday;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class DateInfo {

	private LocalDate date;
	
	private Holiday holiday;	
	
	public DateInfo(LocalDate date) {
		this.date = date;
	}
	
	public DateInfo(LocalDate date
				   ,Holiday holiday) {
		this.date = date;
		this.holiday = holiday;
	}		
	
	public LocalDate getDate() {
		return date;
	}

	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}
	
	public boolean isHoliday() {
		return this.holiday != null ? true : false;
	}
	
	public boolean isWeekend() {		
		return this.date.getDayOfWeek() == DayOfWeek.SATURDAY || this.date.getDayOfWeek() == DayOfWeek.SUNDAY ? true : false; 
	}
	
	public boolean isSaturDay() {		
		return this.date.getDayOfWeek() == DayOfWeek.SATURDAY; 
	}
	
	public boolean isSunday() {		
		return this.date.getDayOfWeek() == DayOfWeek.SUNDAY; 
	}
	
	public String getDayOfWeek() {
		return this.date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
	}
	
	public static void main(String[] args) {
		DateInfo date = new DateInfo(LocalDate.now());
		
		System.out.println(date.getDayOfWeek());
	}
}
