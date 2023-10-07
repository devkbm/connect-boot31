package com.like.hrm.workchangeapp.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.workchangeapp.application.port.dto.WorkChangeCodeDTO;
import com.like.hrm.workchangeapp.application.service.DutyCodeQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class DutyCodeQueryController {

	private DutyCodeQueryService service;
	
	public DutyCodeQueryController(DutyCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/dutycode")
	public ResponseEntity<?> getDutyCodeList(WorkChangeCodeDTO.SearchDutyCode dto) {
								
		
		List<WorkChangeCodeDTO.SaveDutyCode> list = service.getDutyCodeList(dto)
													 .stream()
													 .map(e -> WorkChangeCodeDTO.SaveDutyCode.convert(e))
													 .collect(Collectors.toList());
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
