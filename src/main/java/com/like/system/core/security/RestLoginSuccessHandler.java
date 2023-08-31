package com.like.system.core.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.user.application.service.SystemUserService;
import com.like.system.user.domain.SystemUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();

	private static final ObjectMapper mapper = new ObjectMapper();
	
	//@Resource
	//private UserService loginService;
	
	@Autowired HttpSession session;
	
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

        log.info(authentication.getName() + "로그인 성공");        
 		// log.info(SecurityContextHolder.getContext().getAuthentication().getName());		
 						
 		response.setContentType("application/json;charset=UTF-8");
 		/*
 		SystemUser user = loginService.getFullUser(authentication.getName());
 		// log.info(user.getMenuGroupList().toString());
 								
 		AuthenticationToken token = AuthenticationToken
 										.builder()
 										.userId(user.getUsername())
 										.userName(user.getName())
 										.imageUrl(user.getImage())
 										.collection(user.getAuthorities().stream().map(o -> o.getAuthority()).collect(Collectors.toList()))
 										.menuGroupList(user.getMenuGroupList())
 										.token(this.session.getId())
 										.build();

 		String str = mapper.writeValueAsString(token);
 		 
 		PrintWriter pw = response.getWriter();
 		pw.write(str);
 		pw.flush();
		*/
        SavedRequest savedRequest = requestCache.getRequest(request, response);                
                
        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
                        
        String targetUrlParam = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }        
        
        clearAuthenticationAttributes(request);
        
        

                
	}
			
}
