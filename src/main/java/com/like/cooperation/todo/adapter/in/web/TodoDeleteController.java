package com.like.cooperation.todo.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.application.port.in.TodoDeleteUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class TodoDeleteController {

	TodoDeleteUseCase useCase;
	
	TodoDeleteController(TodoDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/todo/group/{groupid}/todo/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long groupid
									   ,@PathVariable Long id) {							
			
		useCase.delete(groupid, id);
											 				
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
