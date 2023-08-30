package com.like.system.holiday.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.holiday.application.port.in.DateInfoSelectUseCase;
import com.like.system.holiday.domain.DateInfo;

@RestController
public class HolidayQueryController {

	private DateInfoSelectUseCase useCase;
	
	public HolidayQueryController(DateInfoSelectUseCase useCase) {
		this.useCase = useCase;
	}
		
	@GetMapping("/api/system/holiday")
	public ResponseEntity<?> getHolidayList(@RequestParam String organizationCode
			                               ,@RequestParam @DateTimeFormat(pattern="yyyyMMdd") LocalDate fromDate
										   ,@RequestParam @DateTimeFormat(pattern="yyyyMMdd") LocalDate toDate) {
		
		List<DateInfo> list = useCase.select(organizationCode, fromDate, toDate).getDates();			
					
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
