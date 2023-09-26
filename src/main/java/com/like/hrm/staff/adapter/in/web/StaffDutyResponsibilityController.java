package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.application.port.dto.StaffDutyResponsibilityDTO;
import com.like.hrm.staff.application.service.StaffDutyResponsibilityService;
import com.like.hrm.staff.application.service.StaffQueryService;
import com.like.hrm.staff.domain.model.dutyresponsibility.StaffDuty;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffDutyResponsibilityController {

	private StaffDutyResponsibilityService service;
	private StaffQueryService queryService;
	
	public StaffDutyResponsibilityController(StaffDutyResponsibilityService service
											,StaffQueryService queryService) {
		this.service = service;
		this.queryService = queryService;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/dutyresponsibility")
	public ResponseEntity<?> getList(@RequestParam String organizationCode, @PathVariable String staffId) {
				
		List<ResponseStaffDutyResponsibility> list = this.queryService.getStaffDutyResponsibility(organizationCode, staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/dutyresponsibility/{seq}")
	public ResponseEntity<?> get(@RequestParam String organizationCode
								,@PathVariable String staffId
								,@PathVariable Long seq) {
				
		StaffDuty entity = service.get(organizationCode, staffId, seq);  									
				
		var dto = StaffDutyResponsibilityDTO.Form.convert(entity) ;
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
	@PostMapping("/api/hrm/staff/{staffId}/dutyresponsibility")
	public ResponseEntity<?> save(@Valid @RequestBody StaffDutyResponsibilityDTO.Form dto) {			
									
		service.save(dto);
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}