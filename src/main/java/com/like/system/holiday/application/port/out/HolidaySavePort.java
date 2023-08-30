package com.like.system.holiday.application.port.out;

import com.like.system.holiday.application.port.in.dto.HolidaySaveDTO;

public interface HolidaySavePort {
	void save(HolidaySaveDTO dto);
}
