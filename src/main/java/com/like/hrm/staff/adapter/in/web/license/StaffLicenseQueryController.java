package com.like.hrm.staff.adapter.in.web.license;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;
import com.like.hrm.staff.application.port.in.license.StaffLicenseQueryUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffLicenseQueryController {

	StaffLicenseQueryUseCase useCase;
	
	StaffLicenseQueryController(StaffLicenseQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/license")
	public ResponseEntity<?> getLicense(@RequestParam String organizationCode, @PathVariable String staffId) {
						
		List<StaffLicenseSaveDTO> list = useCase.select(organizationCode, staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
