package com.like.system.biztypecode.adapter.out.persistence.jpa.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "BIZCODE")
public class JpaBizCode extends AbstractAuditEntity {

	@EmbeddedId
	JpaBizCodeId id;
	
	@Column(name="CODE_NM")
	String codeName;
		
	@Column(name="USE_YN")
	Boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Column(name="CMT")
	String comment;
	
	@ManyToOne
	@MapsId("bizCodeTypeId")	//기본키를 외래키로 쓰는경우 @MapsId 사용, 아니면 @JOinColumn 사용
	@JoinColumns({
        @JoinColumn(name="org_cd", referencedColumnName="org_cd"),
        @JoinColumn(name="type_id", referencedColumnName="type_id")
    })
	JpaBizCodeType bizCodeType;

	/*
	public JpaBizCode(JpaBizCodeType bizType
			         ,String code
			         ,String name            
					 ,String comment) {
		Objects.requireNonNull(bizType, "업무 구분은 필수 입력 값입니다.");
		if (!StringUtils.hasText(code)) throw new IllegalArgumentException("CODE는 필수 입력 값입니다.");
		
		this.bizCodeType = bizType;
		this.id = new JpaBizCodeId(bizType.getId().getOrganizationCode(), bizType.getId().getTypeId(), code);
		this.codeName = name;
		this.useYn = true;
		this.sequence = 0;
		this.comment = comment;
	}
	*/
	
	@Builder
	public JpaBizCode(JpaBizCodeType bizType
			      ,String code
			      ,String name
			      ,Boolean useYn
			      ,Integer sequence 
				  ,String comment) {
		Objects.requireNonNull(bizType, "업무 구분은 필수 입력 값입니다.");
		if (!StringUtils.hasText(code)) throw new IllegalArgumentException("CODE는 필수 입력 값입니다.");
		
		this.bizCodeType = bizType;
		this.id = new JpaBizCodeId(bizType.getId().getOrganizationCode(), bizType.getId().getTypeId(), code);
		this.codeName = name;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
	
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modify(String codeName
					  ,Boolean useYn
					  ,Integer sequence
					  ,String comment) {
		this.codeName = codeName;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
	
}
