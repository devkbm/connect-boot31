package com.like.cooperation.todo.application.port.in;

import com.like.cooperation.todo.application.port.dto.TodoGroupSaveDTO;

public interface TodoGroupSaveUseCase {
	void save(TodoGroupSaveDTO dto);
}
