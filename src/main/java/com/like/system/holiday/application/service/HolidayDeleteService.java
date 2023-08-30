package com.like.system.holiday.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.like.system.holiday.application.port.in.HolidayDeleteUseCase;
import com.like.system.holiday.application.port.out.HolidayDeletePort;

@Service
public class HolidayDeleteService implements HolidayDeleteUseCase {

	HolidayDeletePort port;
	
	public HolidayDeleteService(HolidayDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, LocalDate date) {
		this.port.delete(organizationCode, date);		
	}

}
