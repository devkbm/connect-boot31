package com.like.system.role.adapter.out.persistence.jpa.entity;

import java.io.Serializable;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "comrole")
public class JpaRole extends AbstractAuditEntity implements Serializable {
	
	private static final long serialVersionUID = 2010711918583959763L;

	@EmbeddedId
	JpaRoleId id;
	
	@Column(name="description")
	String description;	
	
	public JpaRole(String organizationCode, String authorityCode, String description) {		
		this.id = new JpaRoleId(organizationCode, authorityCode);
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
