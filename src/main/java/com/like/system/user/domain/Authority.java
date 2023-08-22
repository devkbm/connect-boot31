package com.like.system.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "comauthority")
public class Authority extends AbstractAuditEntity implements GrantedAuthority {

	private static final long serialVersionUID = 5255280527856714047L;
	
	/*
	@Id
	@Column(name="AUTH_ID")
	String id;
	
	@Column(name="ORG_CD")
	String organizationCode;

	@Column(name="AUTH_CD")
	String authorityCode;
	*/
	@EmbeddedId
	AuthorityId id;
	
	@Column(name="description")
	String description;	
	
	public Authority(String organizationCode, String authorityCode, String description) {		
		this.id = new AuthorityId(organizationCode, authorityCode);
		//this.organizationCode = organizationCode;
		//this.authorityCode = authorityCode;
		this.description = description;
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
	}
	
	@Override
	public String getAuthority() {
		return this.getAuthorityCode();
	}

	/*
	public String getId() {
		return id;
	}
	*/
	
	public String getOrganizationCode() {
		return this.id.getOrganizationCode();
	}

	public String getAuthorityCode() {
		return this.id.getAuthorityCode();
	}
	
	public String getDescription() {
		return description;
	}
	
}
