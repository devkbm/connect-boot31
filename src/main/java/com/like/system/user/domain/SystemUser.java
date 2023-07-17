package com.like.system.user.domain;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.dept.domain.Dept;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.user.domain.vo.AccountSpec;
import com.like.system.user.domain.vo.SystemUserProfilePicture;
import com.like.system.user.domain.vo.UserPassword;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "comuser")
public class SystemUser extends AbstractAuditEntity implements UserDetails {	
	
	private static final long serialVersionUID = -4328973281359262612L;

	/**
	 * 조직코드 + 직원번호
	 */
	@Id
	@Column(name="USER_ID")
	String id;
	
	@Embedded
	StaffId staffId;
	
	/*
	@Column(name="ORG_CD")
	String organizationCode;
	
	@Column(name="STAFF_NO")
	String staffNo;
	*/
	
	@Column(name="USER_NAME")
	String name;
			
	@Embedded
	UserPassword password;
		
	@Embedded
	AccountSpec accountSpec;	
	
	@Column(name="MOBILE_NUM")
	String mobileNum;
	
	@Column(name="EMAIL")
	String email;
				
	//@Column(name="FK_FILE")
	//String image;
	@Embedded
	SystemUserProfilePicture image;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "DEPT_CD", nullable = true)
	Dept dept;
		
	@Setter
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="COMUSERAUTHORITY",
    		joinColumns= @JoinColumn(name="USER_ID"),
    		inverseJoinColumns=@JoinColumn(name="AUTH_ID"))	
	Set<Authority> authorities = new LinkedHashSet<>();
			
	@Setter
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="COMUSERMENUGROUP",
    		joinColumns= @JoinColumn(name="USER_ID"),
    		inverseJoinColumns=@JoinColumn(name="MENU_GROUP_ID"))	
	Set<MenuGroup> menuGroupList = new LinkedHashSet<>();		
		
	@Builder
	public SystemUser(String organizationCode
					 ,String staffNo					 
					 ,String name					 
					 ,UserPassword password
					 ,Dept dept
					 ,String mobileNum
					 ,String email
					 ,AccountSpec accountSpec
					 ,Set<Authority> authorities
					 ,Set<MenuGroup> menuGroupList) {		
		this.id = organizationCode + staffNo;
		this.staffId = new StaffId(organizationCode, staffNo);
		//this.organizationCode = organizationCode;
		//this.staffNo = staffNo;
		this.name = name;		
		this.password = password;
		this.dept = dept;
		this.mobileNum = mobileNum;
		this.email = email;
		this.accountSpec = accountSpec;		
		this.authorities = authorities;
		this.menuGroupList = menuGroupList;
		
		this.initPassword();
	}	
	
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modifyEntity(String organizationCode
							,String staffNo
			 				,String name					 				
							,String mobileNum
							,String email							 
							,Dept dept
							,Set<Authority> authorities
							,Set<MenuGroup> menuGroupList) {
		this.staffId = new StaffId(organizationCode, staffNo);
		//this.organizationCode = organizationCode;
		//this.staffNo = staffNo;
		this.name = name;						
		this.mobileNum = mobileNum;
		this.email = email;		
		this.dept = dept;
		this.authorities = authorities;
		this.menuGroupList = menuGroupList;
	}
	
	@Override	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Set<Authority> getAuthoritiesList() {
		return this.authorities;
	}
			
	@Override	
	public String getUsername() {		
		return id;
	}

	@Override		
	public String getPassword() {
		return password.getPassword();
	}		

	@Override
	public boolean isAccountNonExpired() {
		return true;
		//return accountSpec.getIsAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
		//return accountSpec.getIsAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
		//return accountSpec.getIsCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return true;
		//return accountSpec.getIsEnabled();
	}			
	
	public boolean isVaild(String password) {
		return this.password.matchPassword(password);
	}		
		
	public void addAuthoritiy(Authority authority) {
		if (this.authorities == null) {
			this.authorities = new LinkedHashSet<>();
		}
		
		this.authorities.add(authority);
	}								
	
	public void changePassword(String password) {
		this.password = new UserPassword(password);
	}
	
	/**
	 * 비밀번호를 초기화한다. 
	 * 초기화 비밀번호 : 12345678
	 */
	public void initPassword() {
		if (this.password == null) {
			this.password = new UserPassword();
		} else {
			this.password.init();
		}
	}
	
	public String getImage() {
		if (this.image == null) return null;
		
		return this.image.getImage();
	}
	
	public String changeImage(ProfilePictureRepository localFileRepository, MultipartFile sourceFile) {
		if (this.image == null) this.image = new SystemUserProfilePicture(localFileRepository);		
		
		return this.image.changeImage(localFileRepository, sourceFile);
	}	
	
}
