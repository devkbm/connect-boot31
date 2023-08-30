package com.like.system.holiday.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.holiday.application.port.in.HolidaySelectUseCase;
import com.like.system.holiday.application.port.in.dto.HolidaySaveDTO;

@RestController
public class HolidaySelectController {

	private HolidaySelectUseCase useCase;			
	
	public HolidaySelectController(HolidaySelectUseCase useCase) {
		this.useCase = useCase;			
	}		
	
	@GetMapping("/api/system/holiday/{date}")
	public ResponseEntity<?> getHoliday(@RequestParam String organizationCode,
			                            @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate date) {
		
		/*
		HolidayDTO.Form dto = HolidayDTO.Form.convert(holidayService.getHoliyday(organizationCode,date)
																	.orElse(new Holiday(new HolidayId(organizationCode, date), "", "")));
		*/
		HolidaySaveDTO dto = this.useCase.select(organizationCode, date);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}		
}
