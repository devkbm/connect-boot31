package com.like.cooperation.todo.application.port.dto;

import com.like.cooperation.todo.domain.TodoGroup;

public record TodoGroupSaveDTO(			
		String clientAppUrl,
		String organizationCode,
		Long pkTodoGroup,
		String todoGroupName
		) {
	
	public void modifyEntity(TodoGroup entity) {
		entity.modify(todoGroupName);
	}
}