package com.like.hrm.staff.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.LicenseDTO;
import com.like.hrm.staff.domain.model.license.StaffLicense;
import com.like.hrm.staff.service.StaffLicenseService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffLicenseController {

	private StaffLicenseService service;
		
	public StaffLicenseController(StaffLicenseService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/license")
	public ResponseEntity<?> getLicense(@RequestParam String organizationCode, @PathVariable String staffId) {
						
		List<LicenseDTO.Form> list = service.getLicenseList(organizationCode, staffId)
										    .stream()
										    .map(e -> LicenseDTO.Form.convert(e))
										    .toList();
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/license/{id}")
	public ResponseEntity<?> getLicense(@RequestParam String organizationCode
									   ,@PathVariable String staffId
									   ,@PathVariable Long id) {
				
		StaffLicense license = service.getLicense(organizationCode, staffId, id);
		
		LicenseDTO.Form dto = LicenseDTO.Form.convert(license); 
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/staff/{staffId}/license")
	public ResponseEntity<?> saveLicense(@Valid @RequestBody LicenseDTO.Form dto) {						
				
		service.saveLicense(dto);
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/staff/{staffId}/license/{seq}")
	public ResponseEntity<?> deleteFamily(@RequestParam String organizationCode
										 ,@PathVariable String staffId
									     ,@PathVariable Long seq) {
						
		service.deleteLicense(organizationCode, staffId, seq);  									
		
		return toOne(null, MessageUtil.getDeleteMessage(1));									
	}
}
