package com.like.cooperation.workcalendar.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.cooperation.workcalendar.boundary.WorkCalendarDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarMember;
import com.like.cooperation.workcalendar.domain.WorkCalendarMemberId;
import com.like.cooperation.workcalendar.domain.WorkCalendarMemberRepository;
import com.like.cooperation.workcalendar.domain.WorkCalendarRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserRepository;

@Service
@Transactional
public class WorkCalendarService {

	private WorkCalendarRepository repository;
	private WorkCalendarMemberRepository workGroupMemberRepository;
	private SystemUserRepository userRepository;
	
	public WorkCalendarService(WorkCalendarRepository repository
						   ,WorkCalendarMemberRepository workGroupMemberRepository	
						   ,SystemUserRepository userRepository) {		
		this.repository = repository;
		this.workGroupMemberRepository = workGroupMemberRepository;
		this.userRepository = userRepository;
	}						
	
	/**
	 * 업무그룹를 조회한다.
	 * @param id
	 * @return
	 */
	public WorkCalendar getWorkGroup(Long id) {
		return repository.findById(id).orElse(null);
	}			
	
	public void saveWorkGroup(WorkCalendarDTO.Form dto) {
		WorkCalendar entity = null;
		
		if (dto.workCalendarId() != null) {
			entity = repository.findById(dto.workCalendarId()).orElse(null);
		}
		
		if (entity == null) {
			entity = dto.newWorkGroup();
		} else {
			dto.modifyWorkGroup(entity);
		}
		
		List<String> dtoMemberList = dto.memberList();
		entity.clearWorkGroupMember();
		
		if (dtoMemberList != null) {
			List<SystemUser> userList = userRepository.findAllById(dtoMemberList);
			
			for ( SystemUser user: userList ) {
				WorkCalendarMember member = new WorkCalendarMember(entity, user);				
				entity.addWorkGroupMember(member);
			}
			//workGroupService.saveWorkGroupMember(entity, user);
		}	
				
		repository.save(entity);
	}
	
	public void deleteWorkGroup(Long id) {
		repository.deleteById(id);
	}
	
	public WorkCalendarMember getWorkGroupMember(WorkCalendarMemberId id) {
		return workGroupMemberRepository.findById(id).orElse(null);
	}
	
	public void saveWorkGroupMember(WorkCalendar workGroup, SystemUser user) {
		workGroup.addWorkGroupMember(new WorkCalendarMember(workGroup, user));
	}
	
	public void saveWorkGroupMember(WorkCalendar workGroup, List<SystemUser> userList) {		
		for (SystemUser user: userList) {
			workGroup.addWorkGroupMember(new WorkCalendarMember(workGroup, user));
		}
		
		repository.save(workGroup);
	}

	public void deleteWorkGroupMember(WorkCalendarMember workGroupMember) {
		workGroupMemberRepository.delete(workGroupMember);
	}
		

	
}
