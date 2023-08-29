package com.like.system.user.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthority;
import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthorityId;
import com.like.system.authority.adapter.out.persistence.jpa.repository.AuthorityJpaRepository;
import com.like.system.authority.domain.Authority;
import com.like.system.dept.adapter.out.persistence.jpa.repository.DeptJpaRepository;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptId;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupId;
import com.like.system.menu.domain.MenuGroupRepository;
import com.like.system.user.boundary.SystemUserDTO;
import com.like.system.user.domain.ProfilePictureRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserAuthority;
import com.like.system.user.domain.SystemUserAuthorityRepository;
import com.like.system.user.domain.SystemUserId;
import com.like.system.user.domain.SystemUserMenuGroup;
import com.like.system.user.domain.SystemUserMenuGroupRepository;
import com.like.system.user.domain.SystemUserRepository;

@Transactional
@Service
public class SystemUserService {	
	
	private SystemUserRepository repository;					
	private MenuGroupRepository menuRepository;	
	private DeptJpaRepository deptRepository;		
	private AuthorityJpaRepository authorityRepository;			
	private ProfilePictureRepository profilePictureRepository;
	private SystemUserAuthorityRepository systemUserAuthorityRepository;
	private SystemUserMenuGroupRepository systemUserMenuGroupRepository;
	
	public SystemUserService(SystemUserRepository repository
					  		,AuthorityJpaRepository authorityRepository
					  		,SystemUserAuthorityRepository systemUserAuthorityRepository
					  		,SystemUserMenuGroupRepository systemUserMenuGroupRepository
					  		,MenuGroupRepository menuRepository
					  		,DeptJpaRepository deptRepository
					  		,ProfilePictureRepository profilePictureRepository) {
		this.repository = repository;
		this.menuRepository = menuRepository;
		this.deptRepository = deptRepository;
		this.authorityRepository = authorityRepository;	
		this.systemUserAuthorityRepository = systemUserAuthorityRepository;
		this.systemUserMenuGroupRepository = systemUserMenuGroupRepository;
		this.profilePictureRepository = profilePictureRepository;
	}
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();				
	
	/**
	 * 사용자 도메인을 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 도메인
	 */
	public SystemUser getUser(String organiztionCode, String userId) {								
		return repository.findById(new SystemUserId(organiztionCode, userId)).orElse(null);
	}
		
	public SystemUser getFullUser(String organiztionCode, String userId) {
		SystemUser user = repository.findById(new SystemUserId(organiztionCode, userId)).orElse(null);
							
		return user;				
	}
	
	/**
	 * 사용자를 생성한다.
	 * @param user	사용자 도메인
	 */
	public void saveUser(SystemUserDTO.FormSystemUser dto) {
		SystemUser user = null;
		
		if (dto.userId() != null) {
			user = repository.findById(new SystemUserId(dto.organizationCode(), dto.userId())).orElse(null); 
		}
		
		Dept dept = StringUtils.hasText(dto.deptCode()) ? deptRepository.findById(new DeptId(dto.organizationCode(), dto.deptCode())).orElse(null) : null; 
																															
		if (user == null) {
			user = dto.newUser(dept);
		} else {
			dto.modifyUser(user, dept);			
		}
		
		if (user.getUsername() == null) {
			new IllegalArgumentException("유저 아이디가 존재하지 않습니다.");
		}
		
		/*
		if ( user.getAuthoritiesList().isEmpty() ) {
			initAuthority(user);
		}			
		*/
				
		repository.save(user);

		saveSystemUserAuhority(dto);
		saveMenuGroupList(dto);
	}
		
	public void saveUser(SystemUser user) {
		repository.save(user);
	}
	
	/**
	 * 사용자 정보를 삭제한다.
	 * @param userId	사용자 아이디
	 */
	public void deleteUser(String organiztionCode, String userId) {
		repository.deleteById(new SystemUserId(organiztionCode, userId));         
	}	
	
	public String changeUserImage(String organiztionCode, String userId, MultipartFile file) {
		SystemUser user = repository.findById(new SystemUserId(organiztionCode, userId)).orElseThrow();
		
		if (user == null) return null;
						
		String path = user.changeImage(profilePictureRepository, file);
		
		repository.save(user);
		
		return path;
	}
	
	/**
	 * 사용자 비밀번호를 변경한다.
	 * @param userId
	 * @param beforePassword	변경전 비밀번호
	 * @param afterPassword		변경후 비밀번호
	 */
	public void changePassword(String organiztionCode, String userId, String beforePassword, String afterPassword) {
		SystemUser user = repository.findById(new SystemUserId(organiztionCode, userId)).orElseThrow();			
		
		if ( user.isVaild(beforePassword) ) {
			user.changePassword(afterPassword);
		} 
	}
	
	/**
	 * 사용자의 비밀번호를 초기화한다.
	 * @param userId	사용자 아이디
	 */
	public void initPassword(String organiztionCode, String userId) {
		SystemUser user = repository.findById(new SystemUserId(organiztionCode, userId)).orElseThrow();
				
		user.initPassword();		
	}			
	
	/**
	 * 사용자 권한 리스트를 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 권한 리스트
	 */
	public Set<SystemUserAuthority> getUserAuthorities(String organiztionCode, String userId) {        									
        return repository.findById(new SystemUserId(organiztionCode, userId)).orElse(null).getAuthoritiesList();
	}
					
	
	/**
	 * 중복 유저 검증 기능
	 * @param userId
	 * @return 기존 아이디가 있으면 true, 아니면 false 리턴
	 */
	public boolean CheckDuplicationUser(String organiztionCode, String userId) {						
		return repository.existsById(new SystemUserId(organiztionCode, userId)); 
	}	
	
	public PasswordEncoder passwordEncoder(){
		return this.passwordEncoder;
	}
			
	/**
	 * 사용자 신규등록시 권한이 없을 경우 기본 권한을 추가한다.
	 * @param user	사용자 도메인
	 */
	private void initAuthority(SystemUser user) {							
		user.addAuthoritiy(authorityRepository.findById(new JpaAuthorityId("001", "ROLE_USER")).orElse(null));
	}
	
	private List<JpaAuthority> getAuthorities(String organizationCode, List<String> authorities) {
		return authorityRepository.findAllById(authorities.stream()
														  .map(r -> new JpaAuthorityId(organizationCode, r))
														  .toList());
	}
	
	private List<MenuGroup> getMenuGroupList(String organizationCode, List<String> menuGroupList) {
		return menuRepository.findAllById(menuGroupList.stream()
													   .map(r -> new MenuGroupId(organizationCode, r))
													   .toList());
	}
	
	private void saveMenuGroupList(SystemUserDTO.FormSystemUser dto) {
		SystemUser user = repository.findById(new SystemUserId(dto.organizationCode(), dto.userId())).orElse(null);
		List<MenuGroup> menuGroupList = this.getMenuGroupList(dto.organizationCode(), dto.menuGroupList());
		this.systemUserMenuGroupRepository.saveAll(menuGroupList.stream().map(r -> new SystemUserMenuGroup(user, r)).toList());
		
	}
	
	private void saveSystemUserAuhority(SystemUserDTO.FormSystemUser dto) {
		SystemUser user = repository.findById(new SystemUserId(dto.organizationCode(), dto.userId())).orElse(null);
		List<JpaAuthority> authorityList = this.getAuthorities(dto.organizationCode(), dto.authorityList());		
		this.systemUserAuthorityRepository.saveAll(authorityList.stream().map(r -> new SystemUserAuthority(user, r)).toList());
	}
}
