package com.like.system.menu.application.port.dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuRoleMappingHierarchyResponseDTO {

	/* NzTreeNodeOptions */
	String key;
	
	String title;			
					
	boolean checked;
	
	boolean expanded;
	
	boolean selected;
	
	boolean isLeaf;
	
	List<MenuRoleMappingHierarchyResponseDTO> children;
	/* NzTreeNodeOptions */
	
	String menuGroupCode;
	
	String menuCode;
	
	String roleCode;
	
	@QueryProjection
	public MenuRoleMappingHierarchyResponseDTO(
			String key, 
			String title,
			boolean checked,
			String menuGroupCode,
			String menuCode,
			String roleCode) {
		this.key = key;
		this.title = title;
		this.checked = checked;
		this.expanded = false;
		this.selected = false;
		
		this.menuGroupCode = menuGroupCode;
		this.menuCode = menuCode;
		this.roleCode = roleCode;
				
	}
	
	public void setChildren(List<MenuRoleMappingHierarchyResponseDTO> children) {
		this.children = children;
	}
	
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
