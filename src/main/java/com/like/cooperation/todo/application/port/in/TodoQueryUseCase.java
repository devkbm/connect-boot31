package com.like.cooperation.todo.application.port.in;

import java.util.List;

import com.like.cooperation.todo.application.port.dto.TodoSaveDTO;

public interface TodoQueryUseCase {

	List<TodoSaveDTO> select(Long todoGroupId);
}
