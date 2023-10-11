package com.like.system.menu.domain;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import jakarta.persistence.EmbeddedId;

public class MenuRoleMapping extends AbstractAuditEntity {

	@EmbeddedId
	MenuRoleMappingId id;
}
