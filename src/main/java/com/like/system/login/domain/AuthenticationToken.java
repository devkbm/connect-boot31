package com.like.system.login.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.user.domain.SystemUser;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthenticationToken implements Serializable {
		
	private static final long serialVersionUID = 7987811233360490990L;
	
	private String organizationCode;
	private String userId;
	private String userName;	
	private String staffNo;
	private String email;
	private String imageUrl;
	private String ipAddress;
	private String sessionId;
	private String oAuthAccessToken;
	private List<String> roleList;
    private List<MenuGroupSaveDTO> menuGroupList;
    
       
    @Builder
    public AuthenticationToken(String organizationCode
    						  ,String userId
    						  ,String userName    						  
    						  ,String staffNo 
    						  ,String email
    						  ,String imageUrl
    						  ,String ipAddress
    						  ,String sessionId
    						  ,String oAuthAccessToken
    						  ,List<String> roleList
    						  ,List<MenuGroupSaveDTO> menuGroupList) {
    	
    	this.organizationCode = organizationCode;
    	this.userId = userId;
    	this.userName = userName;    	
    	this.staffNo = staffNo;        
    	this.email = email;
    	this.imageUrl = imageUrl;
    	this.ipAddress = ipAddress;
        this.sessionId = sessionId;
        this.oAuthAccessToken = oAuthAccessToken;
        this.roleList = roleList;
        this.menuGroupList = menuGroupList;        
    }     
    
    public static AuthenticationToken of(SystemUser user, List<MenuGroupSaveDTO> menuGroupList, String ipAddress, String sessionId) {    	
    	    	
    	return AuthenticationToken
				.builder()
				.organizationCode(user.getStaffId().getOrganizationCode())
				.userId(user.getUsername())
				.userName(user.getName())				
				.staffNo(user.getStaffId().getStaffNo())
				.email(user.getEmail())
				.imageUrl(user.getImage())
				.ipAddress(ipAddress)
				.sessionId(sessionId)
				.roleList(user.getAuthorities().stream().map(e -> e.getAuthority()).toList())
				//.menuGroupList(menuGroupList.stream().map(e -> new HtmlSelectOptionRecord(e.menuGroupName(), e.menuGroupCode())).toList())				
				.menuGroupList(menuGroupList)
				.build();
    }
       
    public static void main(String[] vd) {
    	BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    	System.out.println(b.encode("1234"));    	
    }
}
