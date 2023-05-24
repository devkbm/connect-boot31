package com.like.system.menu.domain;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"menuGroup"})
@Entity
@Getter
@Table(name = "commenu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends AbstractAuditEntity implements Serializable {			
	
	private static final long serialVersionUID = -8469789281288988098L;

	@Id
	@Comment("메뉴ID")
	@Column(name = "MENU_ID")
	String id;
	
	@Comment("조직코드")
	@Column(name="ORG_CD")
	String organizationCode;
	
	@Comment("메뉴코드")
	@Column(name="MENU_CODE")
	String code;
	
	@Comment("메뉴명")
	@Column(name="MENU_NAME")
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
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="P_MENU_ID", nullable = true )
	Menu parent;
	
	@JsonIgnore	
	@ManyToOne
	@JoinColumn(name = "MENU_GROUP_ID", nullable=false, updatable=false)
	MenuGroup menuGroup = new MenuGroup();	
		
	@Builder
	public Menu(@NonNull MenuGroup menuGroup,
				String organizationCode,
				String menuCode, 
				String menuName, 				 			
				MenuType menuType,
				String appUrl,
				long sequence,
				long level) {
		
		this.id = menuGroup.getId() + menuCode;
		this.organizationCode = organizationCode;
		this.code = menuCode;
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
