package com.like.system.holiday.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.holiday.application.port.in.HolidayDeleteUseCase;

@RestController
public class HolidayDeleteController {

	private HolidayDeleteUseCase useCase;			
	
	public HolidayDeleteController(HolidayDeleteUseCase useCase) {
		this.useCase = useCase;			
	}			
	
	@DeleteMapping("/api/system/holiday/{id}")
	public ResponseEntity<?> delHoliday(@RequestParam String organizationCode,
			                            @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate id) {						
												
		useCase.delete(organizationCode, id);		
								 						
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
