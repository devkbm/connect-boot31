package com.like.hrm.staff.adapter.in.web.dutyresponsibility;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.application.port.in.dutyresponsibility.StaffDutyResponsibilityQueryUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffDutyResponsibilityQueryController {

	StaffDutyResponsibilityQueryUseCase useCase;
	
	public StaffDutyResponsibilityQueryController(StaffDutyResponsibilityQueryUseCase useCase) {
		this.useCase = useCase;		
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/dutyresponsibility")
	public ResponseEntity<?> getList(@RequestParam String organizationCode, @PathVariable String staffId) {
				
		List<ResponseStaffDutyResponsibility> list = this.useCase.select(organizationCode, staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
