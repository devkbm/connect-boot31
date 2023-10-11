package com.like.system.menu.application.port.dto;

import java.util.List;

public class MenuRoleMappingHierarchyResponseDTO {

	/* NzTreeNodeOptions */
	String key;
	
	String title;			
					
	boolean expanded;
	
	boolean selected;
	
	boolean isLeaf;
	
	List<MenuRoleMappingHierarchyResponseDTO> children;
	/* NzTreeNodeOptions */
	
	public MenuRoleMappingHierarchyResponseDTO(String key, String title) {
		this.key = key;
		this.title = title;
		this.expanded = false;
		this.selected = false;
	}
	
	public void setChildren(List<MenuRoleMappingHierarchyResponseDTO> children) {
		this.children = children;
	}
	
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
