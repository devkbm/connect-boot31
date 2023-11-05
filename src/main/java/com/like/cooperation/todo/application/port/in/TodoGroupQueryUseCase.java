package com.like.cooperation.todo.application.port.in;

import java.util.List;

import com.like.cooperation.todo.domain.TodoGroup;

public interface TodoGroupQueryUseCase {

	List<TodoGroup> select(String userId);
}
