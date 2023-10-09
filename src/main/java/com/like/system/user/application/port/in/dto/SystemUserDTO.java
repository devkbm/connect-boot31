package com.like.system.user.application.port.in.dto;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;

import com.like.system.dept.domain.Dept;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.role.domain.Role;
import com.like.system.user.domain.QSystemUser;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserRole;
import com.like.system.user.domain.vo.AccountSpec;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class SystemUserDTO {
	
	public record Search(
			@NotBlank(message="조직 코드를 선택해주세요.")
			String organizationCode,
			String staffNo,			
			String name,
			String deptCode
			) {
		
		private static final QSystemUser qType = QSystemUser.systemUser;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(eqOrganizationCode(this.organizationCode))
				   .and(likeStaffNo(this.staffNo))
				   .and(likeUserName(this.name))
			 	   .and(equalDeptCode(this.deptCode));						
			
			return builder;
		}
		
		private BooleanExpression eqOrganizationCode(String organizationCode) {
			return qType.staffId.organizationCode.eq(organizationCode);
		}
				
		private BooleanExpression likeStaffNo(String staffNo) {
			return hasText(staffNo) ? qType.staffId.staffNo.like("%"+staffNo+"%") : null;					
		}
		
		private BooleanExpression likeUserName(String name) {
			return hasText(name) ? qType.name.like("%"+name+"%") : null;					
		}
		
		private BooleanExpression equalDeptCode(String deptCode) {
			return hasText(deptCode) ? qType.dept.id.deptCode.eq(deptCode) : null;					
		}
	}		
	
	@Builder
	public static record FormSystemUser(
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
			List<String> authorityList,
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
		
		public static SystemUserDTO.FormSystemUser convertDTO(SystemUser entity) {					
			
			if (entity == null) return null;
			
			Optional<Dept> dept = Optional.ofNullable(entity.getDept());			
			
			FormSystemUser dto = FormSystemUser.builder()								
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
											   .authorityList(entity.getAuthoritiesList()
																	.stream()
																	.map(auth -> auth.getAuthority())
																	.toList())
											   .menuGroupList(entity.getMenuGroupList()
																	.stream()
																	.map(menuGroup -> menuGroup.getMenuGroupCode())
																	.toList())
											   .build();
			
			return dto;
		}
	}
	
	
	

}
