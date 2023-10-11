package com.like.system.menu.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "commenurole")
@EntityListeners(AuditingEntityListener.class)
public class MenuRoleMapping extends AbstractAuditEntity {

	@EmbeddedId
	MenuRoleMappingId id;
	
	@Builder
	public MenuRoleMapping(MenuRoleMappingId id) {
		this.id = id;
	}
}
