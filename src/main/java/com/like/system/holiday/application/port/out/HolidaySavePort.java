package com.like.system.holiday.application.port.out;

import com.like.system.holiday.domain.Holiday;

public interface HolidaySavePort {
	void save(Holiday dto);
}
