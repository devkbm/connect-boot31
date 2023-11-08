package com.like.system.login.domain.event;

import java.time.LocalDate;

public record LoginSuccessEvent(
		String organizationCode,
		String userId,
		LocalDate loginDateTime,
		String clientIp
		) {
}
