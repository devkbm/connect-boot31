package com.like.system.user.domain;

import org.springframework.security.core.GrantedAuthority;

import com.like.system.authority.domain.Authority;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "COMUSERAUTHORITY")
public class SystemUserAuthority extends AbstractAuditEntity implements GrantedAuthority {
	
	private static final long serialVersionUID = 8196330930609694251L;

	@EmbeddedId
	SystemUserAuthorityId id;
	
	@MapsId("userId") 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORG_CD", referencedColumnName = "ORG_CD"),
		@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	})
	SystemUser systemUser;
	
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORG_CD", referencedColumnName = "ORG_CD"),
		@JoinColumn(name = "AUTH_CD", referencedColumnName = "AUTH_CD")
	})
	Authority authority;
	*/
	public SystemUserAuthority(SystemUser systemUser, Authority authority) {
		
		this.id = new SystemUserAuthorityId(systemUser.getId().getOrganizationCode()
										   ,systemUser.getId().getUserId()
										   ,authority.getAuthorityCode());
		this.systemUser = systemUser;
		//this.authority = authority;		
	}

	public String getOrganizationCode() {
		return this.id.getUserId().getOrganizationCode();
	}

	public String getUserId() {
		return this.id.getUserId().getUserId();
	}

	public String getAuthorityCode() {
		return this.id.getAuthorityCode();
	}

	@Override
	public String getAuthority() {
		return this.id.getAuthorityCode();
	}
	
}
