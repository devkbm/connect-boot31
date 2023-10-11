package com.like.system.menu.application.port.dto;

import java.util.List;

import com.like.system.menu.domain.MenuType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuHierarchyResponseDTO {
	
	/* NzTreeNodeOptions */
	String key;
	
	String title;			
					
	boolean expanded;
	
	boolean selected;
	
	boolean isLeaf;
	
	List<MenuHierarchyResponseDTO> children;
	/* NzTreeNodeOptions */
	
	String menuGroupCode;
	
	String parentMenuCode;
	
	String menuType;
	
	Long sequence;
	
	Long level;
	
	String url;

	@QueryProjection
	public MenuHierarchyResponseDTO(String key, String title, String menuGroupCode, String parentMenuCode,
			MenuType menuType, Long sequence, Long level, String url) {				
		this.key = key;
		this.title = title;
		this.expanded = false;
		this.selected = false;
		this.menuGroupCode = menuGroupCode;
		this.parentMenuCode = parentMenuCode;
		this.menuType = menuType.toString();
		this.sequence = sequence;
		this.level = level;
		this.url = url;		
		
	}

	public void setChildren(List<MenuHierarchyResponseDTO> children) {
		this.children = children;
	}
	
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
