package com.like.system.menu.domain;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"menuGroup"})
@JsonIgnoreProperties(value = { "menuGroup" })
@Entity
@Getter
@Table(name = "commenu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends AbstractAuditEntity implements Serializable {			
	
	private static final long serialVersionUID = -8469789281288988098L;
	
	@EmbeddedId
	MenuId id;
	
	@Comment("메뉴명")
	@Column(name="MENU_NM")
	String name; 		
				
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Comment("메뉴분류")
	@Column(name="MENU_TYPE")
	MenuType type;
	
	@Comment("APP URL")
	@Column(name="APP_URL")
	String appUrl;
	
	@Comment("순번")
	@Column(name="SEQ")
	long sequence;
	
	@Comment("계층")
	@Column(name="LVL")
	long level;
	
	
	@OneToOne(optional = true)
	@JoinColumns({
		@JoinColumn(name = "ORG_CD", insertable = false, updatable=false),
		@JoinColumn(name = "MENU_GROUP_CD", insertable = false, updatable=false),
		@JoinColumn(name = "P_MENU_CD", insertable = false, updatable=false )
	})	
	Menu parent;
	
	@Column(name="P_MENU_CD")
	String parentMenuCode;
					
	@ManyToOne
	@MapsId("menuGroupId") 	
	@JoinColumns({
		@JoinColumn(name="org_cd", referencedColumnName = "ORG_CD"),
        @JoinColumn(name="menu_group_cd", referencedColumnName = "MENU_GROUP_CD")		
	})	
	MenuGroup menuGroup;	
		
	@Builder
	public Menu(@NonNull MenuGroup menuGroup,
				String organizationCode,
				String menuCode, 
				String menuName, 				 			
				MenuType menuType,
				String appUrl,
				long sequence,
				long level) {
		
		//this.id = menuGroup.getId() + menuCode;
		//this.organizationCode = organizationCode;
		//this.code = menuCode;
		this.id = new MenuId(organizationCode, menuGroup.getId().getMenuGroupCode(), menuCode);
		
		this.name = menuName;			
		this.type = menuType;
		this.sequence = sequence;
		this.level = level;
		this.menuGroup = menuGroup;
		this.appUrl = appUrl;
	}
	
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modifyEntity(String menuName
							,MenuType menuType
							,String appUrl
							,long sequence
							,long level
							,Menu parent
							,MenuGroup menuGroup) {
		this.name = menuName;
		this.type = menuType;
		this.sequence = sequence;
		this.level = level;
		this.parent = parent;
		this.parentMenuCode = parent.getId().getMenuCode();
		this.menuGroup = menuGroup;
		this.appUrl = appUrl;
	}
							
	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}
	
	public void registerAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

}
