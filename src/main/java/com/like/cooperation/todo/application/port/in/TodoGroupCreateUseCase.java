package com.like.cooperation.todo.application.port.in;

import com.like.cooperation.todo.domain.TodoGroup;

public interface TodoGroupCreateUseCase {

	TodoGroup create(String userId);
}
