package com.like.system.menu.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "commenugroup")
@EntityListeners(AuditingEntityListener.class)
public class MenuGroup extends AbstractAuditEntity implements Serializable {		
	
	private static final long serialVersionUID = -638113137072530575L;
	
	@EmbeddedId
	MenuGroupId id;
	
	@Column(name="MENU_GROUP_NM")
	String name; 
		
	@Comment("MENU_GROUP_URL")
	@Column(name="MENU_GROUP_URL")
	String menuGroupUrl;
	
	@Column(name="DESCRIPTION")
	String description;		
			
	@Builder
	public MenuGroup(String organizationCode, String code, String name, String menuGroupUrl, String description) {	
		this.id = new MenuGroupId(organizationCode, code);
		this.name = name;
		this.menuGroupUrl = menuGroupUrl;
		this.description = description;
	}	
	
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modifyEntity(String menuGroupName
							,String menuGroupUrl
							,String description) {
		this.name = menuGroupName;
		this.menuGroupUrl = menuGroupUrl;
		this.description = description;
	}
	
}
