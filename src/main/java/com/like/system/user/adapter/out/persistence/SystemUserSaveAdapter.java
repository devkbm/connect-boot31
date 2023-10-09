package com.like.system.user.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.like.system.dept.adapter.out.persistence.jpa.repository.DeptJpaRepository;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptId;
import com.like.system.menu.adapter.out.persistence.jpa.repository.MenuGroupJpaRepository;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupId;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRole;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRoleId;
import com.like.system.role.adapter.out.persistence.jpa.repository.RoleJpaRepository;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserAuthorityRepository;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserMenuGroupRepository;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.out.SystemUserDbSavePort;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserRole;
import com.like.system.user.domain.SystemUserId;
import com.like.system.user.domain.SystemUserMenuGroup;


@Repository
@Transactional
public class SystemUserSaveAdapter implements SystemUserDbSavePort {

	SystemUserRepository repository;
	DeptJpaRepository deptRepository;	
	RoleJpaRepository authorityRepository;
	MenuGroupJpaRepository menuRepository;
	SystemUserAuthorityRepository systemUserAuthorityRepository;
	SystemUserMenuGroupRepository systemUserMenuGroupRepository;
	
	SystemUserSaveAdapter(SystemUserRepository repository
						 ,DeptJpaRepository deptRepository
						 ,RoleJpaRepository authorityRepository
						 ,MenuGroupJpaRepository menuRepository
						 ,SystemUserAuthorityRepository systemUserAuthorityRepository
						 ,SystemUserMenuGroupRepository systemUserMenuGroupRepository) {
		this.repository = repository;
		this.deptRepository = deptRepository;
		this.authorityRepository = authorityRepository;
		this.menuRepository = menuRepository;
		this.systemUserAuthorityRepository = systemUserAuthorityRepository;
		this.systemUserMenuGroupRepository = systemUserMenuGroupRepository;
	}
	
	@Override
	public void save(SystemUser entity) {
		this.repository.save(entity);		
	}
	
	@Override
	public void save(SystemUserSaveDTO dto) {
		Dept dept = StringUtils.hasText(dto.deptCode()) ? deptRepository.findById(new DeptId(dto.organizationCode(), dto.deptCode())).orElse(null) : null;
		
		SystemUser entity = dto.newUser(dept); 
		
		this.repository.save(entity);
		
		saveSystemUserAuhority(dto);
		saveMenuGroupList(dto);
	}
	
	private List<JpaRole> getAuthorities(String organizationCode, List<String> authorities) {
		return authorityRepository.findAllById(authorities.stream()
														  .map(r -> new JpaRoleId(organizationCode, r))
														  .toList());
	}
	
	private List<MenuGroup> getMenuGroupList(String organizationCode, List<String> menuGroupList) {
		return menuRepository.findAllById(menuGroupList.stream()
													   .map(r -> new MenuGroupId(organizationCode, r))
													   .toList());
	}
	
	private void saveMenuGroupList(SystemUserSaveDTO dto) {
		SystemUser user = repository.findById(new SystemUserId(dto.organizationCode(), dto.userId())).orElse(null);
		List<MenuGroup> menuGroupList = this.getMenuGroupList(dto.organizationCode(), dto.menuGroupList());
		this.systemUserMenuGroupRepository.saveAll(menuGroupList.stream().map(r -> new SystemUserMenuGroup(user, r)).toList());
		
	}
	
	private void saveSystemUserAuhority(SystemUserSaveDTO dto) {
		SystemUser user = repository.findById(new SystemUserId(dto.organizationCode(), dto.userId())).orElse(null);
		List<JpaRole> authorityList = this.getAuthorities(dto.organizationCode(), dto.authorityList());		
		this.systemUserAuthorityRepository.saveAll(authorityList.stream().map(r -> new SystemUserRole(user, r)).toList());
	}
	
}
