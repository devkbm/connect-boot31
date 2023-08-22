package com.like.system.user.domain;

import java.io.Serializable;

import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.menu.domain.MenuGroup;

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
@Table(name = "COMUSERMENUGROUP")
public class SystemUserMenuGroup extends AbstractAuditEntity implements Serializable {
	
	private static final long serialVersionUID = 4674699282157107857L;

	@EmbeddedId
	SystemUserMenuGroupId id;
	
	@MapsId("userId") 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORG_CD", referencedColumnName = "ORG_CD"),
		@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	})
	SystemUser systemUser;
	
	public SystemUserMenuGroup(SystemUser systemUser, MenuGroup menuGroup) {
		this.id = new SystemUserMenuGroupId(systemUser.getId().getOrganizationCode()
										   ,systemUser.getId().getUserId()
										   ,menuGroup.getId().getMenuGroupCode());
		this.systemUser = systemUser;
	}
	
	public String getOrganizationCode() {
		return this.id.getUserId().getOrganizationCode();
	}

	public String getUserId() {
		return this.id.getUserId().getUserId();
	}

	public String getMenuGroupCode() {
		return this.id.getMenuGroupCode();
	}
}
