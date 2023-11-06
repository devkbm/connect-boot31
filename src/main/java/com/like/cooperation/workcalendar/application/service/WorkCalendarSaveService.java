package com.like.cooperation.workcalendar.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarSaveDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarSaveUseCase;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarCommandDbPort;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarMember;
import com.like.system.user.application.port.in.share.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Transactional
@Service
public class WorkCalendarSaveService implements WorkCalendarSaveUseCase {

	WorkCalendarCommandDbPort dbPort;
	SystemUserCommonSelectUseCase userSelectUseCase;
	
	WorkCalendarSaveService(WorkCalendarCommandDbPort dbPort,
							SystemUserCommonSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
	
	@Override
	public void save(WorkCalendarSaveDTO dto) {
		WorkCalendar entity = null;
		
		if (dto.workCalendarId() != null) {
			entity = dbPort.select(dto.workCalendarId());
		}
		
		if (entity == null) {
			entity = dto.newWorkGroup();
		} else {
			dto.modifyWorkGroup(entity);
		}
		
		entity.clearWorkGroupMember();
		
		List<SystemUserId> dtoMemberList = dto.memberList().stream()
														   .map(r -> new SystemUserId(dto.organizationCode(), r))
														   .toList();						
		if (dtoMemberList != null) {						
			List<SystemUser> userList = userSelectUseCase.findUsers(dtoMemberList);
			
			for ( SystemUser user: userList ) {
				WorkCalendarMember member = new WorkCalendarMember(entity, user);				
				entity.addWorkGroupMember(member);
			}			
		}	
				
		dbPort.save(entity);
	}

}
