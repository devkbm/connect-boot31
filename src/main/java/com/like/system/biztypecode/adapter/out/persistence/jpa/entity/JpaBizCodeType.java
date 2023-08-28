package com.like.system.biztypecode.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import org.springframework.util.StringUtils;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "BIZCODETYPE")
public class JpaBizCodeType extends AbstractAuditEntity {

	@EmbeddedId
	JpaBizCodeTypeId id;
	
	@Column(name="TYPE_NM")
	String name;
			
	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Enumerated(EnumType.STRING)
	@Column(name="BIZ_TYPE")
	JpaBizTypeEnum bizType;
		
	@Embedded
	JpaBizRuleComments ruleComments;
		
	@Column(name="CMT")
	String comment;	
		
	@Builder
	public JpaBizCodeType(String organizationCode, String typeId, String name, JpaBizTypeEnum bizType, String comment) {
		if (!StringUtils.hasText(organizationCode)) throw new IllegalArgumentException("ID는 필수 입력 값입니다.");
		if (!StringUtils.hasText(typeId)) throw new IllegalArgumentException("ID는 필수 입력 값입니다."); 
		
		this.id = new JpaBizCodeTypeId(organizationCode, typeId);
		this.name = name;		
		this.sequence = 0;
		this.bizType = bizType;
		this.comment = comment;
	}	
	
	public void modify(String name, Integer sequence, JpaBizRuleComments ruleComments, String comment) {
		this.name = name;		
		this.sequence = sequence;
		this.ruleComments = ruleComments;
		this.comment = comment;
	}	
		
}
