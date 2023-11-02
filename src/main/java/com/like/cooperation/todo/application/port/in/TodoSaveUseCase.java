package com.like.cooperation.todo.application.port.in;

import com.like.cooperation.todo.application.port.dto.TodoSaveDTO;

public interface TodoSaveUseCase {
	void save(TodoSaveDTO dto);
}
