package com.like.system.authority.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "comauthority")
public class Authority extends AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = 5255280527856714047L;
	
	@EmbeddedId
	AuthorityId id;
	
	@Column(name="description")
	String description;	
	
	public Authority(String organizationCode, String authorityCode, String description) {		
		this.id = new AuthorityId(organizationCode, authorityCode);
		this.description = description;
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
	}		
	
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
