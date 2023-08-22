package com.like.system.user.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemUserMenuGroupId implements Serializable {

	SystemUserId userId;
	
	@Column(name="MENU_GROUP_ID")
	String authorityCode;
}
