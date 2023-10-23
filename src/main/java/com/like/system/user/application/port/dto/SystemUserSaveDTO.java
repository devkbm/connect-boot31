package com.like.system.user.application.port.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.like.system.dept.domain.Dept;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.vo.AccountSpec;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SystemUserSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,
		String userId,
		@NotBlank(message="조직코드를 선택해 주세요.")
		String organizationCode,
		@NotBlank(message="직원번호를 입력해 주세요.")
		String staffNo,
		String name,
		String deptCode,
		String deptName,
		String mobileNum,
		String email,
		String imageBase64,
		Boolean accountNonExpired,
		Boolean accountNonLocked,
		Boolean credentialsNonExpired,
		Boolean enabled,
		List<String> roleList,
		List<String> menuGroupList
		) {
	public SystemUser newUser(Dept dept) {
		SystemUser entity = SystemUser.builder()										  
									  .name(this.name)		
									  .organizationCode(this.organizationCode)
									  .staffNo(this.staffNo)
									  .dept(dept)				
									  .mobileNum(this.mobileNum)
									  .email(this.email)					  
									  .accountSpec(new AccountSpec(true, true, true, true))										  										  			 
									  .build();
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
		
	}
				
	public void modifyUser(SystemUser user, Dept dept) {
					
		user.modifyBuilder()			
			.organizationCode(organizationCode)
			.staffNo(staffNo)
			.name(name)
			.mobileNum(mobileNum)
			.email(email)
			.dept(dept)								
			.modify();
		
		user.setAppUrl(clientAppUrl);
	}
	
	public static SystemUserSaveDTO toDTO(SystemUser entity) {					
		
		if (entity == null) return null;
		
		Optional<Dept> dept = Optional.ofNullable(entity.getDept());			
		
		SystemUserSaveDTO dto = SystemUserSaveDTO.builder()								
										   .organizationCode(entity.getStaffId().getOrganizationCode())
										   .userId(entity.getId().getUserId())
										   .staffNo(entity.getStaffId().getStaffNo())
										   .name(entity.getName())												   
										   .deptCode(dept.map(r -> r.getId().getDeptCode()).orElse(""))
										   .deptName(dept.map(Dept::getDeptNameKorean).orElse(""))
										   .mobileNum(entity.getMobileNum())
										   .email(entity.getEmail())
										   .imageBase64(entity.getImage())
										   .enabled(entity.isEnabled())	
										   .accountNonExpired(entity.isAccountNonExpired())
										   .accountNonLocked(entity.isAccountNonLocked())
										   .credentialsNonExpired(entity.isCredentialsNonExpired())
										   .roleList(entity.getRoleList()
														   .stream()
														   .map(auth -> auth.getAuthority())
														   .toList())										   
										   .build();
		
		return dto;
	}
}
