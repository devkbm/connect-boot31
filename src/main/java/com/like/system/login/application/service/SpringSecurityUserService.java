package com.like.system.login.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.login.application.port.in.LoginRequestDTO;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.domain.SystemUserId;

@Transactional
@Service
public class SpringSecurityUserService implements UserDetailsService {

	private SystemUserRepository repository;
	
	public SpringSecurityUserService(SystemUserRepository repository) {
		this.repository = repository;		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginRequestDTO dto = LoginRequestContext.get();
		
		return repository.findById(new SystemUserId(dto.organizationCode(), username))
						 .orElseThrow(() -> new UsernameNotFoundException(username + " is Not Found"));		
	}

}
